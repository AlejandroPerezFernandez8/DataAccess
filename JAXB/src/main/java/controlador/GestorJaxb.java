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
        
        return LD.leer_entero("");
    }

    public void visualizarTodos(Empleados listaempleados) {
        String salida = "";
        for (Empleado empleado : listaempleados.getEmpleado()) {
            salida += empleado.toString() + "\n";
        }
        System.out.println(salida); 
    }

    public void insertarEmpleado(Empleados listaempleados) {
        
        System.out.println("---------INSERTAR EMPLEADO------------");
        
        //Primero verificamos que el empleado no existe 
        int id = LD.leer_entero("Introduszca el id");
        if (buscarEmpleado(listaempleados, id) != null){
            System.out.println("El empleado ya existe");
            return;
        }
        
        //INSERTAMOS EL EMPLEADO
        listaempleados.getEmpleado().add(new Empleado(
                BigInteger.valueOf(id), 
             LD.leer_String("Introduzca el nombre:"),
                BigInteger.valueOf(LD.leer_entero("Introduzca el Deep :")),  
             BigDecimal.valueOf(LD.leer_float("Introduzca el Salario :"))
        ));
    }

    
    
    public void modificarDatos(Empleados listaempleados) {
        
        System.out.println("---------MODIFICAR EMPLEADO------------");
        int id = LD.leer_entero("Introduszca el id");
        Empleado empleadoModificar = buscarEmpleado(listaempleados, id);
        
        if (empleadoModificar == null){
            System.out.println("No existe el empleado");
            return;
        }
        
        empleadoModificar.setNombre(LD.leer_String("Nuevo Nombre"));
        empleadoModificar.setDep(BigInteger.valueOf(LD.leer_entero("Intrduce el nuevo DEEP")));
        empleadoModificar.setSalario(BigDecimal.valueOf(LD.leer_float("Introduzca el nuevo salario")));
        
        System.out.println("Empleado modificado correctamente");
    }
    
    
    
    public void borrarEmpleado(Empleados listaempleados) {
        System.out.println("---------BORRAR EMPLEADO------------");
        int id = LD.leer_entero("Introduszca el id");
        Empleado empleadoBorrar = buscarEmpleado(listaempleados, id);
        
        if (empleadoBorrar == null){
            System.out.println("No existe el empleado");
            return;
        }
        
        listaempleados.getEmpleado().remove(empleadoBorrar);
        System.out.println("Empleado Borrado");
        
    }
    
   
    
    //SE BUSCA LA COINCIDENCIA DE IDs EN LOS EMPLEADOS
    public Empleado buscarEmpleado(Empleados listaEmpleados , int id) {
       Empleado empleadoBuscar = null; 
        
       for (Empleado empleado : listaEmpleados.getEmpleado()) {
           if (id == empleado.getId().intValue()) {
               empleadoBuscar = empleado;
           }
        }
        return empleadoBuscar;
    }
}
