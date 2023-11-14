/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.vo;

/**
 *
 * @author Alejandro.perezferna
 */
public class Producto {
    private final String id_producto;
    private final String nombre;
    private final Integer stock;
    private final float precio;

    public Producto(String id_producto, String nombre, Integer stock, float precio) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    public String getId_producto() {return id_producto;}
    public String getNombre() {return nombre;}
    public Integer getStock() {return stock;}
    public float getPrecio() {return precio;}

    @Override
    public String toString() {
        return nombre;
    }
}
