/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Usuario
 */
public class controladorEjercicio6 {
    
    public ArrayList<Mataderos> extraerDatos(){
        ArrayList <Mataderos> mataderos = new ArrayList<>();
        try {
            //DECLARACION DOM LEER
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbfactory.newDocumentBuilder();
            Document doc_Lector = dBuilder.parse(new File("./src/main/resources/Ejercicio3/Vacas.xml"));
            
            NodeList vacas =  doc_Lector.getElementsByTagName("vaca");
            for (int i = 0; i < vacas.getLength(); i++) {
                boolean existe = false;
                int posicion = 0;
                Node vaca = vacas.item(i);
                Element elementoVaca = (Element) vaca;
                
                String ID_matadero = elementoVaca.getAttributes().item(0).getTextContent();
                
                if (i == 0) {
                   mataderos.add(new Mataderos(ID_matadero));
                }
                
                
                for (int j = 0; j < mataderos.size(); j++) {
                    
                    Mataderos m = mataderos.get(j);
                    if (m.getIdMatadero().equalsIgnoreCase(ID_matadero)){
                       existe = true;
                       posicion = j;
                    }
                }
                
                if (existe == true){
                        mataderos.get(posicion).añadirVaca(elementoVaca.getAttributes().item(1).getTextContent());
                    }else{
                        Mataderos m = new Mataderos(elementoVaca.getAttributes().item(0).getTextContent());
                        mataderos.add(m);
                        mataderos.get(mataderos.size()-1).añadirVaca(elementoVaca.getAttributes().item(1).getTextContent());
                    }
            }
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(controladorEjercicio6.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(controladorEjercicio6.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(controladorEjercicio6.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mataderos;
        
      
    }
    
    
    
    public void escribirXML() {
        try {
            ArrayList<Mataderos> mataderos = extraerDatos();
            
            //DECLARACION DOOM ESCRIBIR
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbuilder = factory.newDocumentBuilder();
            Document docEscritor = dbuilder.newDocument();
            
            Element raiz = docEscritor.createElement("Mataderos");
            docEscritor.appendChild(raiz);
            
            for (Mataderos matadero : mataderos) {
                
                Element Elementomatadero = docEscritor.createElement("matadero");
                Elementomatadero.setAttribute("ID_matadero", matadero.getIdMatadero());
                raiz.appendChild(Elementomatadero);
                
                for (int i = 0; i < matadero.getVacas().size(); i++) {
                    Element Elementvaca = docEscritor.createElement("vaca");
                    Elementvaca.setTextContent(matadero.getVacas().get(i));
                    Elementomatadero.appendChild(Elementvaca);
                }
            }
            
            
            
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Source source = new DOMSource(docEscritor);
            Result result = new StreamResult(new File("./src/main/resources/Ejercicio6/XMLSALIDA.xml"));
            
            transformer.transform(source, result);
            
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(controladorEjercicio6.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(controladorEjercicio6.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(controladorEjercicio6.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    
    
    
}
