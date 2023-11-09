/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.vo;

/**
 *
 * @author Alejandro.perezferna
 */
public class Detalle {
    private final String id_factura;
    private final String id_detalle;
    private final String id_producto;
    private final Integer cantidad;
    private final float precio;

    public Detalle(String id_factura, String id_detalle, String id_producto, Integer cantidad, float precio) {
        this.id_factura = id_factura;
        this.id_detalle = id_detalle;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getId_factura() {return id_factura;}
    public String getId_detalle() {return id_detalle;}
    public String getId_producto() {return id_producto;}
    public Integer getCantidad() {return cantidad;}
    public float getPrecio() {return precio;}

    @Override
    public String toString() {
        return "detalle{" + "id_factura=" + id_factura + ", id_detalle=" + id_detalle + ", id_producto=" + id_producto + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }
}
