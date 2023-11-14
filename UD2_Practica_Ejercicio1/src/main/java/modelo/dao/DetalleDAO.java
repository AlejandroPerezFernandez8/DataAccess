/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alejandro.perezferna
 */
public class DetalleDAO {

    public int crearRegistro(Connection conn, String num_Factura, int i, String id_producto, int cantidad, float precio) throws SQLException {
        String consulta = "insert into detalle values (?,?,?,?,?)";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        sentencia.setString(1, num_Factura);
        sentencia.setInt(2, i);
        sentencia.setString(3, id_producto);
        sentencia.setInt(4, cantidad);
        sentencia.setFloat(5, precio);
        
        return sentencia.executeUpdate();
    }

    
    public float obtenerTotal(Connection conn,String id_factura) throws SQLException{
       String consulta = "select (sum(cantidad * precio)* 1.21) as total from detalle where numfactura like ?";
       PreparedStatement sentencia = conn.prepareStatement(consulta);
       sentencia.setString(1, id_factura);
       
       ResultSet resultado = sentencia.executeQuery();
       if (resultado.next()){
           return resultado.getFloat(1);
       }else{
           return 0;
       }
    }

    public int eliminarDetalles(Connection conn, String idCliente) throws SQLException {
        String consulta = "delete from detalle where numfactura in (select numfactura from factura where idcliente like ?)";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        sentencia.setString(1, idCliente);
        return sentencia.executeUpdate();
    }
    
    
}
