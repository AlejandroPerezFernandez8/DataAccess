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
import java.io.IOException;
import java.io.ObjectInputStream;

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
    
    
    public static void mostrarficheros(String modo) {
        try (
                FileInputStream ficherosalida = new FileInputStream(new File("./src/main/resources/Ejercicio1/datos.dat")); ObjectInputStream OIS = new ObjectInputStream(ficherosalida);) {
            while (true) {
                info INFO = (info) OIS.readObject();

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
            }
        } catch (EOFException EOF) {
            System.out.println("FIN DE FICHERO");
        } catch (IOException ex) {
            System.out.println("IO EXCEPTION");
        } catch (ClassNotFoundException ex) {
            System.out.println("Clase no encontrada");
        }
    }
}