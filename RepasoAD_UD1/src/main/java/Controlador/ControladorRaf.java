/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Alejandro.perezferna
 */
public class ControladorRaf {
    private LectorDatos LD = new LectorDatos();
    
    
    
    
    
     public int menu(){
        int eleccion;
        
        System.out.println("===============MENU====================");
        System.out.println("[1]Visualizar RAF");
        System.out.println("[2]Visualizar 1 registro");
        System.out.println("[3]Insertar 1 registro");
        System.out.println("[4]Eliminar 1 registro");
        System.out.println("[5]Salir");
        
        return LD.leer_entero("");
    }
    
     
     
   /**
    * 
    * @param raf
    * @throws IOException 
    * Registro = ID - Nombre - Edad - Salario
    * Tamaño de registro = 4+20+4+8 : 36bytes
    * 
    */  
    public void visualizarRaf(RandomAccessFile raf) throws IOException {raf.seek(0);
        int id,edad;
        char[] nombre = new char[10];
        double salario; 
        String nombreFinal;
        
        if (raf.length() == 0) {
            System.out.println("El fichero esta vacio");
            return;
        }
        
        raf.seek(0);
        while (raf.getFilePointer() != raf.length()) {            
            
            id = raf.readInt();
            for (int i = 0; i < nombre.length; i++) {
                nombre[i]=raf.readChar();
            }
            
            nombreFinal = new String(nombre);
            
            edad = raf.readInt();
            salario = raf.readDouble();
            
            
            System.out.println("{ ID: " + id+ " Nombre: " + nombreFinal+ " Edad: " + edad + " Salario: " +salario +  "}");
        }
    }

    public void insertarRegistro(RandomAccessFile raf) {
        
    }
    
    
    
    
}
