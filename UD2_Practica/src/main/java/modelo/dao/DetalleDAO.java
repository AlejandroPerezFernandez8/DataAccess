/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    
    
}
