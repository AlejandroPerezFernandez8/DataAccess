/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.vo.Cliente;

/**
 *
 * @author Alejandro.perezferna
 */
public class ClienteDAO {

    public Cliente obtenerDatosCliente(Connection conn,String id_cliente) throws SQLException {
        String consulta = "SELECT * FROM cliente where idcliente = ?";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        sentencia.setString(1, id_cliente);
        
        ResultSet resultado = sentencia.executeQuery();
        
        if (resultado.next()){
            return new Cliente(
                    resultado.getString(1),
                    resultado.getString(2),
                    resultado.getString(3), 
                    resultado.getString(4));
        }
        return null;
    }

  
}
