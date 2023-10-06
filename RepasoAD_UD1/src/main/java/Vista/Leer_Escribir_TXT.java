/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.Lector_Escritor_TXT;
import java.io.File;
import java.io.IOException;


/**
 *
 * @author Alejandro.perezferna
 */
public class Leer_Escribir_TXT {
    public static void main(String[] args) {
        String datos = "";
        
        
        try {
            
            File fichero = new File("./src/main/resources/ficherostxt/datos.txt");
            File ficheronuevo = new File("./src/main/resources/ficherostxt/nuevosDatos.txt");
            
            datos = Lector_Escritor_TXT.leerDatos(fichero);
            Lector_Escritor_TXT.escribirFichero(ficheronuevo,datos);
            
           
            
            
        } catch (IOException ex) {
            System.out.println("IO EXCEPTION");
        }
        
    }
}
