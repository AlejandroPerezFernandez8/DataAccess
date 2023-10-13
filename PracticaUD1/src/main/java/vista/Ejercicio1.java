/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Modelo.info;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author alejandro.perezferna
 */
public class Ejercicio1 {
    static File ficheroInicio = new File("./src/main/resources/Ejercicio1/");
    static File ficheroObjetos = new File("./src/main/resources/Ejercicio1/info.dat");
    public static void main(String[] args) {
        ObjectInputStream x = null;
        try {
            ficheroObjetos.delete();
            //SE RECORRERA TODA LA CARPETA EJERCICIO1 DE RESOURCES
            recorrerListado(ficheroInicio);
         
            x = new ObjectInputStream(new FileInputStream (ficheroObjetos));
            
            System.out.println(x.readObject().getClass());
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                x.close();
            } catch (IOException ex) {
                Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

        public static void recorrerListado(File fichero) {
        FilenameFilter filtro = (dir, name) -> !name.contains("NoCopiar");

        File[] listadoFicheros = fichero.listFiles(filtro);
        
        try(
                ObjectOutputStream OIS = new ObjectOutputStream(new FileOutputStream(ficheroObjetos,true));
           ){
            for (File ficheroEncontrado : listadoFicheros) {
                info info;
                if (ficheroEncontrado.isDirectory()) {
                    info = new info(ficheroEncontrado.getName(), "CARPETA");
                    recorrerListado(ficheroEncontrado);
                    System.out.println(info.toString());
                    OIS.writeObject(info);
                } else {
                    info = new info(ficheroEncontrado.getName(), "FICHERO");
                    System.out.println(info.toString());
                    OIS.writeObject(info);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("FILE NOT FOUND");
        } catch (IOException ex) {
            System.out.println("IO EXCEPTION");
        }
    }
        
        
       


}
    
