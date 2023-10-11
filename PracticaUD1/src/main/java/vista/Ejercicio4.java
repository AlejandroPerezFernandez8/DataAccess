/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Controlador.controladorEjercicio4;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro.perezferna
 */
public class Ejercicio4 {
    //PARA PROBAR BIEN ESTE EJERCICIO EJECUTA PRIMERO EL 3 PARA TENER EL RAF DE LAS VACAS CREADO
    public static void main(String[] args) {
        
        controladorEjercicio4 controlador = new controladorEjercicio4();
        File ficheroRAF = new File("./src/main/resources/Ejercicio3/Vacas.dat");
        boolean salir = false;
        
        if(!ficheroRAF.exists()){
            System.out.println("El fichero raf no existe, por favor ejecua el ejercicio3 primero");
            return;
        }
        
        try(
                RandomAccessFile raf = new RandomAccessFile(ficheroRAF,"rw");
            ) {
            
            while (salir == false) {                
                switch (controlador.menu()) {
                    case 1 -> {controlador.leerTodos(raf);}//Consultar todos los registros
                    case 2 -> {}
                    case 3 -> {}
                    case 4 -> {}
                    case 5 -> {}
                    case 6 -> {}
                    case 10 ->{salir = true;}
                    default -> System.out.println("Opcion incorrecta");
                }
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio4.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio4.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    
    
    
    
}
