/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.VO;

/**
 *
 * @author Juegos
 */
public class Matadero {
     private String ID_matadero;
    private String nombre;
    private String direccion;
    private String telefono;

    public Matadero(String ID_matadero, String nombre, String direccion, String telefono) {
        this.ID_matadero = ID_matadero;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getID_matadero() {
        return ID_matadero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return "Matadero{" +
                "ID_matadero='" + ID_matadero + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
