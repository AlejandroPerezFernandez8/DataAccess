/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Controlador.LectorDatos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alejandro.perezferna
 */
public class Ejercicio2 {
    static LectorDatos LD = new LectorDatos();
    
    public static void main(String[] args) {
        boolean salir = false;
        
        while (salir == false) {            
            switch (LD.leer_entero("QUE DESEA MOSTRAR? \n (1)Ficheros \n (2)Carpetas \n (3)Todo \n (4)Salir ")) {
            case 1 -> {mostrarficheros("f");}
            case 2 -> {mostrarficheros("d");}
            case 3 -> {mostrarficheros("t");}
            case 4 ->  {salir = true;}
            default -> {System.out.println("Opcion incorrecta");}
            }
        }
        
    }
    
    
    private static void mostrarficheros(String modo) {
        char [] aux = new char[60];
        String ficherofinal;
        
         try (
                RandomAccessFile raf = new RandomAccessFile("./src/main/resources/Ejercicio1/info.dat","r");
            ){
            raf.seek(0);
             while (raf.getFilePointer() != raf.length()) {                 
                 for (int i = 0; i < aux.length; i++) {
                     aux[i] = raf.readChar();
                 }
                 ficherofinal = new String(aux);
                 switch (modo) {
                     case "f" -> {
                         if (ficherofinal.contains("[FICHERO]")) {
                            System.out.println(ficherofinal);
                         }
                     }
                     case "d" -> {
                         if (ficherofinal.contains("[CARPETA]")) {
                            System.out.println(ficherofinal);
                         }
                     }
                     case "t" -> {System.out.println(ficherofinal);}
                     default -> throw new AssertionError();
                 }
             }
             
        }   catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } 
    
    
    }
    
    
}
