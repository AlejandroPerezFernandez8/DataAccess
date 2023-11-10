/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Alejandro.perezferna
 */
public class FacturaDAO {

    public int crearFactura(Connection conn, String num_factura, String id_cliente, String id_empleado, Date fecha, boolean cobrada, float iva) throws SQLException {
        String consulta = "insert into factura values (?,?,?,?,?,?)";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        
        sentencia.setString(1, num_factura);
        sentencia.setString(2,id_cliente);
        sentencia.setString(3, id_empleado);
        sentencia.setDate(4, new java.sql.Date(fecha.getTime()));
        sentencia.setBoolean(5, cobrada);
        sentencia.setFloat(6, iva);
        
        return sentencia.executeUpdate();
    }
    
}
