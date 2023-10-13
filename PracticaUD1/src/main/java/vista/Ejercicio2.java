/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Controlador.LectorDatos;
import Modelo.info;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        File ficheroObjetos = new File("./src/main/resources/Ejercicio1/info.dat");
        
         try (
                FileInputStream FOS = new FileInputStream(ficheroObjetos);
                ObjectInputStream OOS = new ObjectInputStream(FOS);
            ){
            info INFO = null; 
             
             while (true) {

                 try {
                     INFO = (info) OOS.readObject();

                     if (INFO != null) {
                         switch (modo) {
                             case "f" -> {
                                 if (INFO.getTipo().equalsIgnoreCase("FICHERO")) {
                                     System.out.println("[" + INFO.getTipo() + "]" + INFO.getNombre());
                                 }
                             }
                             case "d" -> {
                                 if (INFO.getTipo().equalsIgnoreCase("CARPETA")) {
                                     System.out.println("[" + INFO.getTipo() + "]" + INFO.getNombre());
                                 }
                             }
                             case "t" -> {
                                 System.out.println("[" + INFO.getTipo() + "]" + INFO.getNombre());
                             }
                             default ->
                                 throw new AssertionError();
                         }
                     } else {
                         break;  // Si INFO es null, salimos del bucle
                     }
                 } catch (EOFException e) {
                     break;  // Se alcanzó el final del archivo
                 }


            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
            
            
        
    }
    


