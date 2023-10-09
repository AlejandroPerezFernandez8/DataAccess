/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
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
public class ConversorDomToRaf {
    
    public void convertirfichero(){
        
        //SI EL RAF EXISTE LO BORRAMOS PORQUE SOLO LO QUEREMOS COMO SALIDA, NO QUEREMOS CONSERVARLO EN CADA EJECUCION
            File ficheroDatos = new File("./src/main/resources/datosSalida.dat");
            ficheroDatos.delete();
        
        try(
                RandomAccessFile raf = new RandomAccessFile(new File("./src/main/resources/datosSalida.dat"), "rw")
            ) {
            File ficheroxml = new File("./src/main/resources/Vacas.xml");
                    
            if(!ficheroxml.exists()){
                System.out.println("El fichero no existe");
                return;
            }
                
            //DECLARACION DOM
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbfactory.newDocumentBuilder();
            Document doc = dBuilder.parse(ficheroxml);
                    
            NodeList listaVacas = doc.getElementsByTagName("Vaca");
            
                        
            for (int i = 0; i < listaVacas.getLength() ; i++) {
                //ATRIBUTOS DE LAS VACAS
                String ID_vaca,ID_matadero,raza;
                int edad;
                char sexo;
                
                //CONVERSION A ELEMENTO
                Node Nodevaca = listaVacas.item(i);
                Element elementVaca = (Element) Nodevaca;
                
                //SACAMOS LOS ATRIBUTOS
                ID_matadero = AtributosVaca(elementVaca);
                ID_vaca = elementVaca.getElementsByTagName("ID_vaca").item(0).getTextContent();
                raza = elementVaca.getElementsByTagName("raza").item(0).getTextContent();
                sexo = elementVaca.getElementsByTagName("sexo").item(0).getTextContent().charAt(0);
                edad = Integer.parseInt(elementVaca.getElementsByTagName("edad").item(0).getTextContent());
                
                if(ID_matadero.isEmpty()){ID_matadero = "0000000000";} //0 para las vacas que no han sido enviado al matadero
                
                
                //ESCRIBIMOS EN EL RAF
                escribirFicheroRaf(raf, ID_vaca, ID_matadero, raza, sexo, edad);
                
            }
            System.out.println("Fichero Escrito");
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            System.out.println("Excepcion");
        }
    }    



    private void escribirFicheroRaf(RandomAccessFile raf,String ID_vaca,String ID_matadero,String raza , char sexo, int edad) throws IOException{
        //EL RAF SE COMPONDRA DE :
            //ID_vaca (10) , ID_matadero(10),raza(10),sexo(1),edad(4) = 65
        
        //NOS POSICIONAMOS EN LA ULTIMA POSICION
        raf.seek(raf.length());
        
        //String buffer auxiliar para dar tamaño fijo a los strings
        StringBuffer aux;
        
        //ID_VACA
        aux = new StringBuffer(ID_vaca);
        aux.setLength(10);
        raf.writeChars(aux.toString());
        //ID_MATADERO
        aux = new StringBuffer(ID_matadero);
        aux.setLength(10);
        raf.writeChars(aux.toString());
        //Raza
        aux = new StringBuffer(raza);
        aux.setLength(10);
        raf.writeChars(aux.toString());
        //EDAD
        raf.writeInt(edad);
        //Sexo
        raf.writeChar(sexo);  
    }

    
    private String AtributosVaca(Element vaca){
        String ID_matadero = "";
        if(vaca.hasAttributes()){
            for (int i = 0; i < vaca.getAttributes().getLength(); i++) {
                ID_matadero = vaca.getAttributes().item(i).getNodeValue();
            }
        }
        return ID_matadero;
    }
    

    
    
}
