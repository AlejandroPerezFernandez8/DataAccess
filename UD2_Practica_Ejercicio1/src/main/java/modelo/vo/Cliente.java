/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.vo;

/**
 *
 * @author Alejandro.perezferna
 */
public class Cliente {
    private final String id;
    private final String Nombre;
    private final String Apellido;
    private final String Direccion;

    public Cliente(String id, String Nombre, String Apellido, String Direccion) {
        this.id = id;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Direccion = Direccion;
    }
 
    public String getId() {return id;}
    public String getNombre() {return Nombre;}
    public String getApellido() {return Apellido;}
    public String getDireccion() {return Direccion;}

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", Nombre=" + Nombre + ", Apellido=" + Apellido + ", Direccion=" + Direccion + '}';
    }
}
