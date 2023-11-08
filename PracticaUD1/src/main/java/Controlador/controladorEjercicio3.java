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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author alejandro.perezferna
 */
public class controladorEjercicio3 {
    
    //ESCRITURA DEL XML
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
    
    
    //PASAR DEL XML A UN RAF
    public void escribirRAF(RandomAccessFile raf,File ficheroxml) throws IOException{
        try {
            //SE ESCRIBE ID_Vaca, raza,sexo,edad,ID_matadero
            
            StringBuffer aux;
            
            //DECLARACION DOM
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbfactory.newDocumentBuilder();
            Document doc = dBuilder.parse(ficheroxml);
            
            raf.seek(0);
            
            NodeList listaVacas = doc.getElementsByTagName("vaca");
            
            for (int i = 0; i < listaVacas.getLength(); i++) {
               Node nodeVaca = listaVacas.item(i);
                
               Element elementoVaca = (Element) nodeVaca;
               
               //ESCRIBIMOS EL ID VACA
               aux = new StringBuffer(elementoVaca.getAttributes().item(1).getTextContent());
               aux.setLength(10);
               raf.writeChars(aux.toString());
               
               //ESCRIBIMOS LA RAZA
               aux = new StringBuffer(elementoVaca.getElementsByTagName("Raza").item(0).getTextContent());
               aux.setLength(10);
               raf.writeChars(aux.toString()); 
             
               //ESCRIBIMOS EL SEXO
               raf.writeChar(elementoVaca.getElementsByTagName("Sexo").item(0).getTextContent().charAt(0));
               
               //ESCRIBIMOS LA EDAD
               raf.writeInt(Integer.parseInt(elementoVaca.getElementsByTagName("Edad").item(0).getTextContent().trim()));
               
               
               //ESCRIBIMOS EL ID DEL MATADERO
               aux = new StringBuffer(elementoVaca.getAttributes().item(0).getTextContent());
               aux.setLength(10);
               raf.writeChars(aux.toString()); 
                
            }
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(controladorEjercicio3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
