/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

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
    public void leerTodos(RandomAccessFile raf){
        char[] cadenas = new char[10];
        String ID_vaca,ID_matadero,raza;
        char sexo;
        int edad;
        
        try {
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

                System.out.println("{" +"ID_VACA: "+ID_vaca +" RAZA: "+raza+" SEXO: "+sexo +" EDAD: "+edad + "ID_MATADERO: " + ID_matadero + "}");
                System.out.println("------------------------------------------------------------------------------------");
            }
            
        } catch (IOException ex) {
            System.out.println("IO Exception durante la lectura");
        }
        
        
    }
    
    
    //FUNCION PARA INSERTAR UN REGISTRO AL FINAL DEL FICHERO
    public void insertar(RandomAccessFile raf){
        
        try {
            raf.seek(raf.length());
        } catch (IOException ex) {
            Logger.getLogger(controladorEjercicio4.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
