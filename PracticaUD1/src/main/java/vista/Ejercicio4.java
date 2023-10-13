/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Controlador.controladorEjercicio4;
import java.io.File;
/**
 *
 * @author Alejandro.perezferna
 */
public class Ejercicio4 {
    //PARA PROBAR BIEN ESTE EJERCICIO EJECUTA PRIMERO EL 3 PARA TENER EL RAF DE LAS VACAS CREADO
    public static void main(String[] args) {
        
        controladorEjercicio4 controlador = new controladorEjercicio4();
        File ficheroRAF = new File("./src/main/resources/Ejercicio3/Vacas.dat");
        
        boolean salir = false;
        
        if(!ficheroRAF.exists()){
            System.out.println("El fichero raf no existe, por favor ejecua el ejercicio3 primero");
            return;
        }
        
        
            
        while (salir == false) {                
            switch (controlador.menu()) {
                case 1 -> {controlador.leerTodos(new File("./src/main/resources/Ejercicio3/Vacas.dat"));}//Consultar todos los registros
                case 2 -> {controlador.insertar(new File("./src/main/resources/Ejercicio3/Vacas.dat"));}//Insertar 1 registro
                case 3 -> {controlador.borrar(new File("./src/main/resources/Ejercicio3/Vacas.dat"),new File("./src/main/resources/Ejercicio4/raftmp.dat"));}//Borrar 1 registro
                case 5 -> {controlador.modificarID(new File("./src/main/resources/Ejercicio3/Vacas.dat"));}
                case 6 -> {controlador.modificarFK(new File("./src/main/resources/Ejercicio3/Vacas.dat"));}
                case 10 ->{salir = true;}
                default -> System.out.println("Opcion incorrecta");
            }
        }
    }

}
