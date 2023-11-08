/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Modelo.Empleados;
import controlador.GestorJaxb;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro.perezferna
 */
public class Marshal_Unmarshal {
    static GestorJaxb gestor = new GestorJaxb();
    
    public static void main(String[] args) {
        try {
            boolean salir = false;
            
            //**********************UNMARSHAL**************
            JAXBContext contexto = JAXBContext.newInstance(Empleados.class);
            Unmarshaller unmarshallerObj = contexto.createUnmarshaller();
            Empleados listaempleados = (Empleados) unmarshallerObj.unmarshal(new File("./src/main/resources/empleadosjaxb.xml"));
            
                        
            //ELECCIONES DEL MENU
            while (salir == false) {
                switch (gestor.menu()) {
                    case 1 -> {gestor.visualizarTodos(listaempleados);}
                    case 2 -> {gestor.modificarDatos(listaempleados);}
                    case 3 -> {gestor.insertarEmpleado(listaempleados);}
                    case 4 -> {gestor.borrarEmpleado(listaempleados);}
                    case 5 -> {salir = true;}
                    default -> System.out.println("Opcion Invalida");
                }
            }
            System.out.println("Saliendo...");
            
            //*******************MARSHALL**********
            Marshaller marshallerObj = contexto.createMarshaller();
            marshallerObj.marshal(listaempleados,new File("./src/main/resources/nuevosempleado.xml"));
              
            
            
            
            
        } catch (JAXBException ex) {
            Logger.getLogger(Marshal_Unmarshal.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    
    
}
