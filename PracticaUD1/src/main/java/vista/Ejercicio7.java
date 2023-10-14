/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Modelo.*;
import Controlador.controladorEjercicio7;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Usuario
 */
public class Ejercicio7 {
    
    public static void main(String[] args) {
        try {
            boolean salir = false;
            controladorEjercicio7 gestor = new controladorEjercicio7();
            
            //**********************UNMARSHAL**************
            JAXBContext contexto = JAXBContext.newInstance(Vacas.class);
            Unmarshaller unmarshallerObj = contexto.createUnmarshaller();
            Vacas listaVacas = (Vacas) unmarshallerObj.unmarshal(new File("./src/main/resources/Ejercicio7/Vacas.xml"));
                    
            //ELECCIONES DEL MENU
            while (salir == false) {
                switch (gestor.menu()) {
                    case 1 -> {gestor.visualizarTodos(listaVacas);}
                    case 2 -> {gestor.modificarDatos(listaVacas);}
                    case 3 -> {gestor.insertarVaca(listaVacas);}
                    case 4 -> {gestor.borrarVaca(listaVacas);}
                    case 5 -> {salir = true;}
                    default -> System.out.println("Opcion Invalida");
                }
            }
            System.out.println("Saliendo...");        
                    
            
            
            //*******************MARSHALL**********
            Marshaller marshallerObj = contexto.createMarshaller();
            marshallerObj.marshal(listaVacas,new File("./src/main/resources/Ejercicio7/NuevoVacas.xml"));
            
        } catch (JAXBException ex) {
            Logger.getLogger(Ejercicio7.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
