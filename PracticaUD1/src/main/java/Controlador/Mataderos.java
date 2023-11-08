/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Mataderos {
    String idMatadero;
    ArrayList<String> vacas = new ArrayList<>();

    public String getIdMatadero() {
        return idMatadero;
    }

    public ArrayList<String> getVacas() {
        return vacas;
    }

    public void setIdMatadero(String idMatadero) {
        this.idMatadero = idMatadero;
    }

    public void setVacas(ArrayList<String> vacas) {
        this.vacas = vacas;
    }
    
    public void añadirVaca(String ID_vaca){
        this.vacas.add(ID_vaca);
    }

    @Override
    public String toString() {
        return "Mataderos{" + "idMatadero=" + idMatadero + ", vacas=" + vacas + '}';
    }

    public Mataderos(String idMatadero) {
        this.idMatadero = idMatadero;
    }
    
    
    
}
