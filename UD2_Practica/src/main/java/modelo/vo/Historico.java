/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.vo;

/**
 *
 * @author Alejandro.perezferna
 */
public class Historico {
    private final String id_cliente;
    private final String nombreYapellidos;
    private final float  importeFacturado;
    private final String Observaciones;

    public Historico(String id_cliente, String nombreYapellidos, float importeFacturado, String Observaciones) {
        this.id_cliente = id_cliente;
        this.nombreYapellidos = nombreYapellidos;
        this.importeFacturado = importeFacturado;
        this.Observaciones = Observaciones;
    }

    public String getId_cliente() {return id_cliente;}
    public String getNombreYapellidos() {return nombreYapellidos;}
    public float getImporteFacturado() {return importeFacturado;}
    public String getObservaciones() {return Observaciones;}

    @Override
    public String toString() {
        return "Historico{" + "id_cliente=" + id_cliente + ", nombreYapellidos=" + nombreYapellidos + ", importeFacturado=" + importeFacturado + ", Observaciones=" + Observaciones + '}';
    }
}
