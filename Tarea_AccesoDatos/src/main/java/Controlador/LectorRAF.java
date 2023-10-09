/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author alejandro.perezferna
 */
public class LectorRAF {
    
    public void leerRaf(RandomAccessFile raf) throws IOException{
        String ID_vaca,ID_matadero,raza; 
        char sexo;
        int edad;        
        char[] aux = new char[10];//La usaré para leer las strings
        
        raf.seek(0);
        
        while(raf.getFilePointer() != raf.length()){
            
            //LEER ID DE LA VACA
            for (int i = 0; i < aux.length; i++) {
                aux[i] = raf.readChar();
            }
            ID_vaca = new String(aux);
            
            //LEER ID DE MATADERO
            for (int i = 0; i < aux.length; i++) {
                aux[i] = raf.readChar();
            }
            ID_matadero = new String(aux);
            
            //LEER RAZA
            for (int i = 0; i < aux.length; i++) {
                aux[i] = raf.readChar();
            }
            raza = new String(aux);
               
            //LEER EDAD
            edad = raf.readInt();
            //LEER SEXO
            sexo = raf.readChar();
            
            
            System.out.println("{ ID_VACA : " + ID_vaca +" ID_MATADERO : "+ID_matadero +" RAZA : "+ raza+" EDAD : "+edad  + " SEXO : " +sexo  +"}");
            
        }
        
        
        
    }
    
    
    
    
    
    
    
}
