/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author alejandro.perezferna
 */
public class controladorEjercicio3 {
    
     public void escribirXML(){
         File ficheroxml =  new File("./src/main/resources/Ejercicio3/Vacas.xml");
         
         if(ficheroxml.exists()){ficheroxml.delete();}
         
         //DATOS PARA RELLENAR EL XML
         String[] ID_Vaca = {"vac_000001","vac_000002","vac_000003"};
         String[] ID_Matadero = {"mat_000001","mat_000002","mat_000001"};
         String[] Raza = {"Raza1","Raza1","Raza3"};
         char[] sexo = {'M','F','M'};
         int[] edad = {10,3,5};
         
         try(
                FileWriter escritor = new FileWriter(ficheroxml);
                BufferedWriter bw = new BufferedWriter(escritor);
            ){
            
             bw.write("<Vacas>");
             for (int i = 0; i < ID_Vaca.length; i++) {
                 bw.write("<vaca id_vaca=\""+ID_Vaca[i]+"\" id_matadero=\""+ID_Matadero[i]+"\" >");
                    bw.write("<Raza>");
                        bw.write(Raza[i]);
                    bw.write("</Raza>");
                    bw.write("<Sexo>");
                        bw.write(sexo[i]);
                    bw.write("</Sexo>");
                    bw.write("<Edad>");
                        bw.write(String.valueOf(edad[i]));
                    bw.write("</Edad>");
                 bw.write("</vaca>");
             }
             bw.write("</Vacas>");
         } catch (IOException ex) {
             System.out.println("IO EXCEPTION");
         } 
     }
    
    
    
    
    
    
    
    
    
}
