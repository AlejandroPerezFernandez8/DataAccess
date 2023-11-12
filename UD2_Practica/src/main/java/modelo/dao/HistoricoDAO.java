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
public class HistoricoDAO {

    public int crearRegistro(Connection conn, String idCliente, String Nombre, String Apellido, float totalFacturas, String nombreFacturas) throws SQLException {
        String consulta = "insert into historicofacturadoporcliente (idcliente,nomapecli,importefacturado,observaciones) values (?,?,?,?)";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        
        sentencia.setString(1, idCliente);
        sentencia.setString(2, Nombre + "--" + Apellido);
        sentencia.setFloat(3, totalFacturas);
        sentencia.setString(4, nombreFacturas);
        
        return sentencia.executeUpdate();
    }
    
}
