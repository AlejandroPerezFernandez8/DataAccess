/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Modelo.Empleado;
import Modelo.Empleados;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro.perezferna
 */
public class Deserializacion_UnMarshall {
    public static void main(String[] args) {
        
        try {
            
            JAXBContext contexto = JAXBContext.newInstance(Empleados.class);
            Unmarshaller unmarshallerObj = contexto.createUnmarshaller();
            Empleados listaempleaedos = (Empleados) unmarshallerObj.unmarshal(new File("./src/main/resources/empleadosjaxb.xml"));
            
            System.out.println(visualizar(listaempleaedos));
            
        } catch (JAXBException ex) {
            Logger.getLogger(Deserializacion_UnMarshall.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static String visualizar(Empleados listaempleaedos) {
        String cadena = "";
        for (Empleado e : listaempleaedos.getEmpleado()) {
            cadena += e.toString() + "\n";
        }
        
        return cadena;
    }
}
