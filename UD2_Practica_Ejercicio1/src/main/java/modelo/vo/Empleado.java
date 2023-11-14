/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.vo;

/**
 *
 * @author Alejandro.perezferna
 */
public class Empleado {
    private final String id_empleado;
    private final String nombre;
    private final float salario;
    private final float incentivo;
    private final int operativas;

    public Empleado(String id_empleado, String nombre, float salario, float incentivo, int operativas) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.salario = salario;
        this.incentivo = incentivo;
        this.operativas = operativas;
    }

    public String getId_empleado() {return id_empleado;}
    public String getNombre() {return nombre;}
    public float getSalario() {return salario;}
    public float getIncentivo() {return incentivo;}
    public int getOperativas() {return operativas;}

    @Override
    public String toString() {
        return nombre;
    }
}
