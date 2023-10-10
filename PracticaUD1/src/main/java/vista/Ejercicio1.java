/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Controlador.LectorDatos;
import Modelo.info;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author alejandro.perezferna
 */
public class Ejercicio1 {
    static File ficheroInicio = new File("./src/main/resources/Ejercicio1/");
    static LectorDatos LD = new LectorDatos();
    public static void main(String[] args) {
        new File(ficheroInicio+"info.dat").delete();
        //SE RECORRERA TODA LA CARPETA EJERCICIO1 DE RESOURCES
        recorrerListado(ficheroInicio);
       
        
        
    }

    public static void recorrerListado(File fichero){
        
        File[] listadoFicheros = fichero.listFiles();
        
        for (File ficheroencontrado : listadoFicheros) {
            info info;
            if (ficheroencontrado.isDirectory()) {
                
                info = new info(ficheroencontrado.getName(),"CARPETA");
                recorrerListado(ficheroencontrado);
                EscribirObjeto(info);
            }else{
                info = new info(ficheroencontrado.getName(),"FICHERO");
                EscribirObjeto(info);
            }
        }
    }
    
    public static void EscribirObjeto(info info){
        //ESCRIBIREMOS LOS DATOS EN EJERCICIO1/info.dat
        try (
                RandomAccessFile raf = new RandomAccessFile(ficheroInicio+"/info.dat","rw");
            ){
               StringBuffer cadena;
               raf.seek(raf.length());
               if (info.getTipo().equalsIgnoreCase("CARPETA")) {
                   cadena = new StringBuffer("[CARPETA] " + info.getNombre());
               }else{
                   cadena = new StringBuffer("[FICHERO] " + info.getNombre());
               }
               
               cadena.setLength(60);
               raf.writeChars(cadena.toString());
                            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private static void mostrarRaf() {
        char [] aux = new char[60];
        String ficherofinal;
        
         try (
                RandomAccessFile raf = new RandomAccessFile(ficheroInicio+"info.dat","r");
            ){
            raf.seek(0);
             while (raf.getFilePointer() != raf.length()) {                 
                 for (int i = 0; i < aux.length; i++) {
                     aux[i] = raf.readChar();
                 }
                 ficherofinal = new String(aux);
                 System.out.println(ficherofinal);
             }
             
        }   catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } 
    
    
    }
}
