/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.ConversorDomToRaf;
import Controlador.LectorRAF;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author alejandro.perezferna
 */
public class Main {
    static ConversorDomToRaf cdtr = new ConversorDomToRaf();
    static LectorRAF LR = new LectorRAF();
    
    public static void main(String[] args) {
        
        try (
                RandomAccessFile raf = new RandomAccessFile("./src/main/resources/datosSalida.dat", "rw");
            ){
             //SI EL RAF EXISTE LO BORRAMOS PORQUE SOLO LO QUEREMOS COMO SALIDA, NO QUEREMOS CONSERVARLO EN CADA EJECUCION
            File ficheroDatos = new File("./src/main/resources/datosSalida.dat");
            ficheroDatos.deleteOnExit();
        
            ConversorDomToRaf DTR = new ConversorDomToRaf();
            DTR.convertirfichero();
            
            LR.leerRaf(raf);
            
        } catch (IOException ex) { 
            System.out.println("IO EXCEPTION");
        }
        
        
         
    }
}
