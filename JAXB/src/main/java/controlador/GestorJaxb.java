/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Modelo.Empleado;
import Modelo.Empleados;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Alejandro.perezferna
 */
public class GestorJaxb {
    private static final LectorDatos LD = new LectorDatos();
    
    public GestorJaxb() {
    }
    
    public int menu(){
        int eleccion;
        
        System.out.println("===============MENU====================");
        System.out.println("[1]Visualizar Todos");
        System.out.println("[2]Modificar");
        System.out.println("[3]Insertar");
        System.out.println("[4]Eliminar Empleado");
        System.out.println("[5]Salir");
        
        eleccion = LD.leer_entero("");
        
        return eleccion;
    }

    public void visualizarTodos(Empleados listaempleados) {
        String salida = "";
        for (Empleado empleado : listaempleados.getEmpleado()) {
            salida += empleado.toString() + "\n";
        }
        System.out.println(salida); 
    }

    public void insertarEmpleado(Empleados listaempleados) {
        
        System.out.println("______INSERTAR EMPLEADO________");
        
        listaempleados.getEmpleado().add(new Empleado(
                BigInteger.valueOf(LD.leer_entero("Introduzca el ID :")), 
             LD.leer_String("Introduzca el nombre:"),
                BigInteger.valueOf(LD.leer_entero("Introduzca el Deep :")),  
             BigDecimal.valueOf(LD.leer_float("Introduzca el Salario :"))
        ));
    }

    public void modificarDatos(Empleados listaempleados) {
        
    }

    public void borrarEmpleado(Empleados listaempleados) {
        
    }
    
}
