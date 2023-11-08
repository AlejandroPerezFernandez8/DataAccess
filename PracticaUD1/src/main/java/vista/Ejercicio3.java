/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Controlador.controladorEjercicio3;
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
public class Ejercicio3 {
    public static void main(String[] args) {
        controladorEjercicio3 controlador = new controladorEjercicio3();
        File ficheroSalida = new File("./src/main/resources/Ejercicio3/Vacas.dat");
        File ficheroXML = new File("./src/main/resources/Ejercicio3/Vacas.xml");
        
        
        if (ficheroSalida.exists()) {
            ficheroSalida.delete();
        }
        
        try (
                RandomAccessFile rafVacas = new RandomAccessFile(ficheroSalida,"rw")
            ){
            
            controlador.escribirXML();
            controlador.escribirRAF(rafVacas,ficheroXML);
            
            
        } catch (FileNotFoundException ex) { 
            Logger.getLogger(Ejercicio3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
}
