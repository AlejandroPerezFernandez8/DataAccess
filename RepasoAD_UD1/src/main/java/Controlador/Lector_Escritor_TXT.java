/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Alejandro.perezferna
 */
public class Lector_Escritor_TXT {

    public static String leerDatos(File fichero) throws IOException {
        
        FileReader FicheroLector = new FileReader(fichero);
        BufferedReader br = new BufferedReader(FicheroLector);
        String linea = "",resultado = "";
        
        
        while ((linea = br.readLine())!= null) {
            resultado += linea+"\n";
        }
        
        FicheroLector.close();
        return resultado;
    }

    public static void escribirFichero(File ficheronuevo, String datos) throws IOException {
        
        FileWriter FicheroEscritor = new FileWriter(ficheronuevo);
        
        FicheroEscritor.write(datos);
        FicheroEscritor.close();
    }
}
