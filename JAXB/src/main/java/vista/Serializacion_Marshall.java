/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Modelo.Empleado;
import Modelo.Empleados;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alejandro.perezferna
 */
public class Serializacion_Marshall {
    public static void main(String[] args) {
        try {
            //Crear 2 objetos empleado y realizar el marshall
            Empleados listaEmpleados = new Empleados();
            
            Empleado empleado1 = new Empleado(BigInteger.ONE,"Pepito",BigInteger.valueOf(100),BigDecimal.valueOf(123.45));
            Empleado empleado2 = new Empleado(BigInteger.TWO,"Manuel",BigInteger.valueOf(200),BigDecimal.valueOf(67.89));
            
            listaEmpleados.getEmpleado().add(empleado1);
            listaEmpleados.getEmpleado().add(empleado2);
            
            //***********************MARSHALL**********************************
            JAXBContext contexto = JAXBContext.newInstance(Empleados.class);
            Marshaller marshallerObj = contexto.createMarshaller();
            marshallerObj.marshal(listaEmpleados,new File("./src/main/resources/nuevosempleado.xml"));
              
        } catch (JAXBException ex) {
            Logger.getLogger(Serializacion_Marshall.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
