/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.VO;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Juegos
 */
public class ModeloTabla extends AbstractTableModel {
     private ArrayList<Vaca> vacas = new ArrayList<>();
    private String[] columnames = {"ID_Vaca", "ID_matadero", "Raza", "Sexo", "Edad", "Tratamientos"};

    public ModeloTabla(ArrayList<Vaca> vacas) {
        this.vacas = vacas;
    }

    public ModeloTabla() {
     
    }
    
    
    @Override
    public int getRowCount() {
        return vacas.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnames[column];
    }

    @Override
    public int getColumnCount() {
        return columnames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Vaca vaca = vacas.get(rowIndex);

         return switch (columnIndex) {
             case 0 -> vaca.getID_vaca();
             case 1 -> vaca.getID_matadero();
             case 2 -> vaca.getRaza();
             case 3 -> vaca.getSexo();
             case 4 -> vaca.getEdad();
             case 5 -> vaca.getTratamientos();
             default -> null;
         };
    }

    public void setVacas(ArrayList<Vaca> vacas) {
        this.vacas = vacas;
        fireTableDataChanged();
    }

    public ArrayList<Vaca> getVacas() {
        return vacas;
    }
    
   
    
}
