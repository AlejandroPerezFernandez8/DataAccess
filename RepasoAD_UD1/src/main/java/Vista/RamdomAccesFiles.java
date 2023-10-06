/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.ControladorRaf;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Alejandro.perezferna
 */
public class RamdomAccesFiles {
    private static final ControladorRaf controlador = new ControladorRaf();
    
    public static void main(String[] args) {
       boolean salir = false;
        
        while (salir == false) {            
            try (
                    RandomAccessFile raf = new RandomAccessFile("./src/main/resources/RAF/raf.dat", "rw");
                ) {
                switch (controlador.menu()) {
                    case 1 -> {controlador.visualizarRaf(raf);}
                    case 2 -> {controlador.insertarRegistro(raf);}
                    case 3 -> {}
                    case 4 -> {}
                    case 5 -> {salir = true;}
                
                    default -> System.out.println("Opcion ivalida");
                }
            }catch(IOException io){
                System.out.println("IO Exception");
            } 
        }
        

        
        
    }
}
