/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Modelo.info;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



/**
 *
 * @author alejandro.perezferna
 */
public class Ejercicio1 {
    
    public static void main(String[] args) {
        File ficheroInicio = new File("./src/main/resources/Ejercicio1/");
        ArrayList<info> ficheros = new ArrayList<>();
        
        new File("./src/main/resources/Ejercicio1/datos.dat").delete();
        
        recorrerListado(ficheroInicio, ficheros);
        EscribirObjetos(ficheros);
        
    }

    public static void recorrerListado(File fichero,ArrayList<info> ficheros) {
        FilenameFilter filtro = (dir, name) -> !name.equalsIgnoreCase("Nocopiar");

        File[] listadoFicheros = fichero.listFiles(filtro);
        
        
            for (File ficheroEncontrado : listadoFicheros) {
                if (ficheroEncontrado.isDirectory()) {
                    info INFO= new info(ficheroEncontrado.getName(), "CARPETA");
                    recorrerListado(ficheroEncontrado,ficheros);
                    //System.out.println(INFO.toString());
                    ficheros.add(INFO);
                } else {
                    info INFO = new info(ficheroEncontrado.getName(), "FICHERO");
                    //System.out.println(INFO.toString());
                    ficheros.add(INFO);
                }
            }
    }
        
    
    public static void EscribirObjetos(ArrayList<info> ficheros){
        
            try(
                    FileOutputStream ficherosalida = new FileOutputStream(new File("./src/main/resources/Ejercicio1/datos.dat"));
                    ObjectOutputStream OOS = new ObjectOutputStream(ficherosalida);
               ) {
                for (info fichero : ficheros) {
                    OOS.writeObject(fichero);
                }
            } catch (Exception e) {
                System.out.println("EXCEPCION EN LA ESCRITURA");
            }
        
    }       
}
    
