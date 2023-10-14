/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.*;

/**
 *
 * @author Usuario
 */
public class controladorEjercicio7 {
    LectorDatos LD = new LectorDatos();
    
      public int menu(){
        
        System.out.println("===============MENU====================");
        System.out.println("[1]Visualizar Todos");
        System.out.println("[2]Modificar");
        System.out.println("[3]Insertar");
        System.out.println("[4]Eliminar Vaca");
        System.out.println("[5]Salir");
        
        return LD.leer_entero("");
    }

    public void visualizarTodos(Vacas listaVacas) {
        for (VacaType vaca : listaVacas.getVaca()) {
            System.out.println(vaca.toString());
        }
    }

    public void modificarDatos(Vacas listaVacas) {
       String ID_Vaca = LD.leer_String("Introduce el ID de la vaca que desea modificar");
        
       for (VacaType vaca : listaVacas.getVaca()) {
            if (vaca.getIdVaca().equalsIgnoreCase(ID_Vaca)) {
               vaca.setEdad(LD.leer_entero("Introduce la nueva edad:"));
               vaca.setRaza(LD.leer_String("Introduce la nueva raza:"));
               vaca.setSexo(LD.leer_String("Introduce el nuevo sexo (M|F)"));
           }
        }
    }    

    public void insertarVaca(Vacas listaVacas) {
        VacaType vaca = new VacaType(
                LD.leer_String("Introduce la Raza:"), 
                LD.leer_String("Introduce el sexo"), 
                LD.leer_entero("Introduce la edad"), 
                LD.leer_String("Introduce el ID de la vaca"), 
                LD.leer_String("Introduce el ID del matdero"));
        
        listaVacas.getVaca().add(vaca);
    }
    
    

    public void borrarVaca(Vacas listaVacas) {
       String ID_Vaca = LD.leer_String("Introduce el ID de la vaca que desea borrar");
        
        for (int i = 0; i < listaVacas.getVaca().size(); i++) {
            VacaType vaca = listaVacas.getVaca().get(i);
           
            if(vaca.getIdVaca().equalsIgnoreCase(ID_Vaca)){
               listaVacas.getVaca().remove(i);
            }
            
        }
       
       
    }

    

    
}
