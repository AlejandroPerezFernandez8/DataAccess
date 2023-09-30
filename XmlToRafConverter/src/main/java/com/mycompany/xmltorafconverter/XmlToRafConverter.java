/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.xmltorafconverter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 *
 * @author Usuario
 * XML TO RAF CONVERTER
 */
public class XmlToRafConverter {
    public static void main(String[] args) {
       StringBuffer nombre,apellido;
       char [] readerRaf = new char[10];
       String aux;
       File rafFile = new File("./src/main/resources/raf.dat");
       rafFile.delete();
       
        try(
             RandomAccessFile raf = new RandomAccessFile(rafFile, "rw");
            ){
            File xmlFile = new File("./src/main/resources/clase.xml");
            
            
            
            if (!xmlFile.exists()){
                System.out.println("El fichero no existe");
                return;
            }
             
            //DOM DECLARATION
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = (Document) builder.parse(xmlFile);
            //Parse the document to nodelist
            NodeList alumnoList = document.getElementsByTagName("alumno");
            
            for (int i = 0; i < alumnoList.getLength(); i++) {
                //Convert Nodelist to node
                Node alumnoNode = alumnoList.item(i);
                //Parse to element
                Element alumnoElement = (Element) alumnoNode;
                
                //RAF REGISTER = 4+10+10+8 : TOTAL = 32bytes
                
                //Write the randomAccessFile
                raf.seek(raf.length());
                raf.writeInt(Integer.parseInt(alumnoElement.getAttribute("id").trim())); 
                //Set the lenght to the strings
                nombre = new StringBuffer(alumnoElement.getElementsByTagName("nombre").item(0).getTextContent().trim());
                nombre.setLength(10);
                apellido = new StringBuffer(alumnoElement.getElementsByTagName("apellidos").item(0).getTextContent().trim());
                apellido.setLength(10);
                
                raf.writeChars(nombre.toString());
                raf.writeChars(apellido.toString());
                
                raf.writeDouble(Double.parseDouble(alumnoElement.getElementsByTagName("media").item(0).getTextContent().trim()));

            }
            
            //READ THE RAF FILE
            raf.seek(0);
            while (raf.getFilePointer() != raf.length()) {                
                System.out.println("___________________________");
                System.out.println("ID: " + raf.readInt());
                
                for (int i = 0; i < readerRaf.length; i++) {
                    readerRaf[i] = raf.readChar();
                }
                aux = new String(readerRaf);
                System.out.println("Nombre: " + aux);
                
                for (int i = 0; i < readerRaf.length; i++) {
                    readerRaf[i] = raf.readChar();
                }
                aux = new String(readerRaf);
                System.out.println("Apellido: " + aux);
                
                System.out.println("Nota Media: " + raf.readDouble());
                
                
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XmlToRafConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
