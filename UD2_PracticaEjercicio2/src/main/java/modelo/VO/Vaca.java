/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.VO;

/**
 *
 * @author Juegos
 */
public class Vaca {
    private String ID_vaca;
    private String ID_matadero;
    private String raza;
    private char sexo;
    private int edad;
    private int tratamientos;

    public Vaca(String ID_vaca, String ID_matadero, String raza, char sexo, int edad, int tratamientos) {
        this.ID_vaca = ID_vaca;
        this.ID_matadero = ID_matadero;
        this.raza = raza;
        this.sexo = sexo;
        this.edad = edad;
        this.tratamientos = tratamientos;
    }

    public String getID_vaca() {
        return ID_vaca;
    }

    public String getID_matadero() {
        return ID_matadero;
    }

    public String getRaza() {
        return raza;
    }

    public char getSexo() {
        return sexo;
    }

    public int getEdad() {
        return edad;
    }

    public int getTratamientos() {
        return tratamientos;
    }

    @Override
    public String toString() {
        return ID_vaca;
    }
    
    
    
    
}
