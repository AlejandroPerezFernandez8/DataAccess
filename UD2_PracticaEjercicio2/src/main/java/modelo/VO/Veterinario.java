/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.VO;

/**
 *
 * @author Juegos
 */
public class Veterinario {
        private String ID_veterinario;
    private String nombre;
    private String apellidos;
    private int edad;
    private String telefono;

    public Veterinario(String ID_veterinario, String nombre, String apellidos, int edad, String telefono) {
        this.ID_veterinario = ID_veterinario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.telefono = telefono;
    }

    public String getID_veterinario() {
        return ID_veterinario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return "Veterinario{" +
                "ID_veterinario='" + ID_veterinario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
