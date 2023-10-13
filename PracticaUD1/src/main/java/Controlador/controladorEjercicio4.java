/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro.perezferna
 */
public class controladorEjercicio4 {
    LectorDatos LD = new LectorDatos();
    
    
    public int menu(){
        int eleccion;
        
        System.out.println("=========MENU PRINCIPAL=======");
        System.out.println("[1]Consultar todos los registros");
        System.out.println("[2]Insertar registro");
        System.out.println("[3]Borrar registro");
        System.out.println("[4]Modificar registro");
        System.out.println("[10]Salir");
        eleccion = LD.leer_entero("");
        if(eleccion == 4){
            System.out.println("[5]Modificar mediante identificar");
            System.out.println("[6]Modificar mediante clave foranea");
            
            eleccion = LD.leer_entero("");
        }
        
        return eleccion;
    }
    
    
    //FUNCION PARA LEER TODO EL RAF
    public void leerTodos(File file){
        char[] cadenas = new char[10];
        String ID_vaca,ID_matadero,raza;
        char sexo;
        int edad;
        
        try (
               RandomAccessFile raf = new RandomAccessFile(file, "rw");
            ){
            raf.seek(0);
            while (raf.getFilePointer() != raf.length()) {
                //Leer ID Vaca                
                for (int i = 0; i < cadenas.length; i++) {
                    cadenas[i] = raf.readChar();
                }
                ID_vaca = new String(cadenas);
                
                //Leer Raza
                for (int i = 0; i < cadenas.length; i++) {
                    cadenas[i] = raf.readChar();
                }
                raza = new String(cadenas);
                //LEER SEXO
                sexo = raf.readChar();
                //LEER EDAD
                edad = raf.readInt();
                //LEER ID MATADERO
                for (int i = 0; i < cadenas.length; i++) {
                    cadenas[i] = raf.readChar();
                }
                ID_matadero = new String(cadenas);

                System.out.println("{" +"ID_VACA: "+ID_vaca +" RAZA: "+raza+" SEXO: "+sexo +" EDAD: "+edad + " ID_MATADERO: " + ID_matadero + "}");
                System.out.println("------------------------------------------------------------------------------------");
            }
            
        } catch (IOException ex) {
            System.out.println("IO Exception durante la lectura");
        }
        
        
    }
    
    //FUNCION PARA INSERTAR UN REGISTRO AL FINAL DEL FICHERO
    public void insertar(File file){
        
        try (
                RandomAccessFile raf = new RandomAccessFile(file, "rw");    
            ){
            
            StringBuffer aux;
            char[] cadena = new char[10];
            String comprobarID;
            
            
            //ESCRIBIMOS EL ID VACA
            aux = new StringBuffer(LD.leer_String("Introduce el ID de la vaca"));
            aux.setLength(10);
            
            
            //ANTES DE ESCRIBIRLO DEBEMOS MIRAR SI EXISTE
            int numero_registros = (int) (raf.length() / 65);
            raf.seek(0);
            for (int i = 0; i < numero_registros; i++) {
                
                for (int j = 0; j < cadena.length; j++) {
                    cadena[j] = raf.readChar();
                }
                
                comprobarID = new String(cadena);
                if (comprobarID.trim().equalsIgnoreCase(aux.toString())) {
                    System.out.println("EL ID PROPORCIONADO YA EXISTE");
                    return;
                }else{
                    raf.skipBytes(46);
                }
            }
            
            raf.seek(raf.length());
            raf.writeChars(aux.toString());
            
            
            //ESCRIBIMOS LA RAZA
               aux = new StringBuffer(LD.leer_String("Introduzca la raza"));
               aux.setLength(10);
               raf.writeChars(aux.toString()); 
             
               //ESCRIBIMOS EL SEXO
               raf.writeChar(LD.leer_String("Escribe el sexo del animal (M|F)").charAt(0));
               
               //ESCRIBIMOS LA EDAD
               raf.writeInt(LD.leer_entero("Introduzca la edad"));
               
               
               //ESCRIBIMOS EL ID DEL MATADERO
               aux = new StringBuffer(LD.leer_String("Escriba el ID del matadero"));
               aux.setLength(10);
               raf.writeChars(aux.toString()); 
            
        } catch (IOException ex) {
            Logger.getLogger(controladorEjercicio4.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //FUNCION DE BORRADO DE UNA TUPLA
    public void borrar(File file,File tmp){
        
        if (tmp.exists()) {tmp.delete();}
        
        try (
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                RandomAccessFile raftemp = new RandomAccessFile(tmp, "rw");
            ){
            StringBuffer aux,converter;
            char[] cadena = new char[10];
            String datos;;
            
            //VAMOS IR LEYENDO Y ESCRIBIENDO EN EL RAF TEMPORAL, SI ENCONTRAMOS UN ID IGUAL AL PROPORCIONADO,
            //NO ESCRIBIREMOS ESA LINEA
            aux = new StringBuffer(LD.leer_String("Introduce el ID de la vaca que desesa borrar"));
            aux.setLength(10);
            
            raf.seek(0);
            raftemp.seek(0);
            while (raf.getFilePointer() != raf.length()) {
                
                //ID VACA
                for (int j = 0; j < cadena.length; j++) {
                    cadena[j] = raf.readChar();
                }

                datos = new String(cadena);
                if (datos.trim().equalsIgnoreCase(aux.toString())) {
                    raf.skipBytes(46);
                    continue;
                }else{
                    converter = new StringBuffer(datos);
                    converter.setLength(10);
                    raftemp.writeChars(converter.toString());

                    //RAZA
                    for (int j = 0; j < cadena.length; j++) {
                        cadena[j] = raf.readChar();
                    }

                    datos = new String(cadena);
                    converter = new StringBuffer(datos);
                    converter.setLength(10);
                    raftemp.writeChars(converter.toString());
                    //SEXO
                    raftemp.writeChar(raf.readChar());
                    //EDAD
                    raftemp.writeInt(raf.readInt());
                    //ID_MATADERO
                    for (int j = 0; j < cadena.length; j++) {
                        cadena[j] = raf.readChar();
                    }

                    datos = new String(cadena);
                    converter = new StringBuffer(datos);
                    converter.setLength(10);
                    raftemp.writeChars(converter.toString());

                }
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION");
        }
        file.delete();
        tmp.renameTo(new File("./src/main/resources/Ejercicio3/Vacas.dat"));
        
    }
    
    //FUNCION PARA MODIFICAR TUPLA MEDIANTE ID
    public void modificarID(File file){
        try(
            RandomAccessFile raf = new RandomAccessFile(file,"rw");
           ){
           
            StringBuffer aux;
            char[] cadena = new char[10];
            String comprobarID;
            
            
            //ESCRIBIMOS EL ID VACA
            aux = new StringBuffer(LD.leer_String("Introduce el ID de la vaca"));
            aux.setLength(10);
            
            
            //ANTES DE ESCRIBIRLO DEBEMOS MIRAR SI EXISTE
            int numero_registros = (int) (raf.length() / 65);
            raf.seek(0);
            for (int i = 0; i < numero_registros; i++) {
                
                for (int j = 0; j < cadena.length; j++) {
                    cadena[j] = raf.readChar();
                }
                
                comprobarID = new String(cadena);
                if (comprobarID.trim().equalsIgnoreCase(aux.toString())) {
                    raf.seek(raf.getFilePointer() -20);
                    
                    raf.writeChars(aux.toString());

                    //ESCRIBIMOS LA RAZA
                    aux = new StringBuffer(LD.leer_String("Introduzca la nueva raza"));
                    aux.setLength(10);
                    raf.writeChars(aux.toString());

                    //ESCRIBIMOS EL SEXO
                    raf.writeChar(LD.leer_String("Escribe el nuevo sexo del animal (M|F)").charAt(0));

                    //ESCRIBIMOS LA EDAD
                    raf.writeInt(LD.leer_entero("Introduzca la nueva edad"));

                    //ESCRIBIMOS EL ID DEL MATADERO
                    aux = new StringBuffer(LD.leer_String("Escriba el nuevo ID del matadero"));
                    aux.setLength(10);
                    raf.writeChars(aux.toString());

                }else{
                    raf.skipBytes(46);
                }
            }
              
        } catch (IOException ex) {
            System.out.println("IO EXCEPTION");
        } 
    }
    
    
    //FUNCION PARA MODIFICAR TODAS LAS TUPLAS EN LAS QUE COINCIDA LA FK
    public void modificarFK(File file){
        //MODIFICAREMOS TODAS LAS EDADES Y LAS PONDREMOS A 0
        try(
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
           ){
            StringBuffer aux;
            char[] cadena = new char[10];
            String comprobarFK;
            
            
            //ESCRIBIMOS EL ID MATADERO
            aux = new StringBuffer(LD.leer_String("Introduce el ID del matadero"));
            aux.setLength(10);
            
            
            int numero_registros = (int) (raf.length() / 65);
            raf.seek(0);
            
            for (int i = 0; i < numero_registros; i++) {
                raf.skipBytes(46);
                
                for (int j = 0; j < cadena.length; j++) {
                    cadena[j] = raf.readChar();
                }
                
                comprobarFK = new String(cadena);
                if (comprobarFK.trim().equalsIgnoreCase(aux.toString())) {
                    raf.seek(raf.getFilePointer() -24);
                    raf.writeInt(0);
                    raf.skipBytes(20);
                }
            }
        } catch (IOException ex) {
            System.out.println("IO EXCEPTION");
        } 
        
        
    }
}
            
  

