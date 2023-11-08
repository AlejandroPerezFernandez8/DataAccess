/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Usuario
 */
public class controladorEjercicio5 {
    
    
    
    
    
    public void obtenerDatos(File file){
        ArrayList <String> ID_Matadero = new ArrayList();
        ArrayList <String> ID_Vaca = new ArrayList();
        
        try(
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
            ){

            StringBuffer aux;
            char[] CA = new char[10];

            int numero_registros = (int) (raf.length() / 65);
            raf.seek(46); 
                      
            for (int i = 0; i < numero_registros; i++) {
                
                for (int j = 0; j < CA.length; j++) {
                    CA[j] = raf.readChar();
                }
           
                aux = new StringBuffer(new String(CA));
                ID_Matadero.add(aux.toString());
                
                raf.seek(raf.getFilePointer() - 66);
                
                for (int j = 0; j < CA.length; j++) {
                    CA[j] = raf.readChar();
                }
           
                aux = new StringBuffer(new String(CA));
                ID_Vaca.add(aux.toString());
                
                raf.skipBytes(92); 
                
                EscribirXML(ID_Matadero, ID_Vaca);
            }
        } catch (IOException ex) {
            Logger.getLogger(controladorEjercicio5.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
    }
    
    
    public void EscribirXML(ArrayList<String> ID_Matadero, ArrayList<String> ID_Vaca) {
    File ficheroxml = new File("./src/main/resources/Ejercicio5/Vacas.xml");
    if (ficheroxml.exists()) {
        ficheroxml.delete();
    }

    try (
            FileWriter escritor = new FileWriter(ficheroxml);
            BufferedWriter bw = new BufferedWriter(escritor)
        ) {

        bw.write("<Mataderos>\n");

        
        for (int i = 0; i < ID_Matadero.size(); i++) {
            String mataderoId = ID_Matadero.get(i);

            
            if (mataderoYaEscrito(mataderoId, ID_Matadero, i)) {
                continue;
            }

            bw.write("  <Matadero id_matadero=\"" + mataderoId + "\">\n");

            
            for (int z = 0; z < ID_Matadero.size(); z++) {
                if (ID_Matadero.get(z).equalsIgnoreCase(mataderoId)) {
                    bw.write("    <Vaca>" + ID_Vaca.get(z) + "</Vaca>\n");
                }
            }

            bw.write("  </Matadero>\n");
        }

        bw.write("</Mataderos>");
    } catch (IOException ex) {
        System.out.println("IO EXCEPTION");
    }
}

private boolean mataderoYaEscrito(String mataderoId, ArrayList<String> ID_Matadero, int iteracion) {
    for (int j = 0; j < iteracion; j++) {
        if (ID_Matadero.get(j).equalsIgnoreCase(mataderoId)) {
            return true;
        }
    }
    return false;
}
    
   

}
