/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.util.Scanner;

/**
 *
 * @author Alejandro.perezFernandez
 * Clase utilizada unicamente para el proceso
 * de pedir datos al usuario y evitar el manejo de errores ya
 * que cada metodo lo hace individualemente
 */
public class LectorDatos {
    Scanner teclado =  new Scanner(System.in);
    
    public int leer_entero(String Mensaje){
        int entero = 0;
        boolean salir = false;
        while(salir == false){
            try {
                System.out.println(Mensaje);
                entero = Integer.parseInt(teclado.nextLine());
                if (entero > 0){salir = true;}else{System.out.println("Los numeros deben de ser mayores a 0");}
            }catch (NumberFormatException NFE){
                System.out.println("Se deben introducir numeros enteros");
            }      
        }
        return entero;
    }
    
    public float leer_float(String Mensaje){
        float numero = 0;
        boolean salir = false;
        while(salir == false){
            try {
                System.out.println(Mensaje);
                numero = Float.parseFloat(teclado.nextLine());
                if (numero > 0){salir = true;}else{System.out.println("Los numeros deben de ser mayores a 0");}
            }catch (NumberFormatException NFE){
                System.out.println("Se debe introducir numeros enteros o flotantes");
            }      
        }
        return numero;
    }
    
    public String leer_String(String Mensaje){
        String datos;
        System.out.println(Mensaje);
        datos = teclado.nextLine();
        return datos;
    }
    
    


}
