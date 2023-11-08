/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Controlador.controladorEjercicio5;
import java.io.File;

/**
 *
 * @author Usuario
 */
public class Ejercicio5 {
    static controladorEjercicio5 controlador = new controladorEjercicio5();
    //SE ESCRIBA UN XML DE MATADEROS DONDE SE GUARDARA EL ID DE LAS VACAS QUE FUERON A CADA MATADERO
    public static void main(String[] args) {
        controlador.obtenerDatos(new File("./src/main/resources/Ejercicio3/Vacas.dat"));
    }
}
