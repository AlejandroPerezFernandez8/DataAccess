/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.vo;

import java.sql.Date;

/**
 *
 * @author Alejandro.perezferna
 */
public class Factura {
    private final String id_factura;
    private final String id_cliente;
    private final String id_empleado;
    private final Date fecha;
    private final boolean cobrada;
    private final double iva = 1.21;

    public Factura(String id_factura, String id_cliente, String id_empleado, Date fecha, boolean cobrada) {
        this.id_factura = id_factura;
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
        this.fecha = fecha;
        this.cobrada = cobrada;
    }

    public String getId_factura() {return id_factura;}
    public String getId_cliente() {return id_cliente;}
    public String getId_empleado() {return id_empleado;}
    public Date getFecha() {return fecha;}
    public boolean isCobrada() {return cobrada;}
    public double getIva() {return iva;}

    @Override
    public String toString() {
        return "Factura{" + "id_factura=" + id_factura + ", id_cliente=" + id_cliente + ", id_empleado=" + id_empleado + ", fecha=" + fecha + ", cobrada=" + cobrada + ", iva=" + iva + '}';
    }
}
