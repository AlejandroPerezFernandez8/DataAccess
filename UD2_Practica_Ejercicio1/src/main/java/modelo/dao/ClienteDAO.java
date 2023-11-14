/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public boolean hasFacturasPagadas(Connection conn,String idcliente) throws SQLException {
        String consulta = "select cobrada from factura where idcliente like ? and cobrada != 1;";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        sentencia.setString(1, idcliente);
        
        ResultSet resultado =  sentencia.executeQuery();
        
        if (resultado.next()){
            //NO TIENE TODAS LAS FACTURAS PAGADAS
            return false;
        }else{
            //TIENE TODAS LAS FACTURAS PAGADAS
            return true;
        }
    }

    public int eliminarCliente(Connection conn, String idcliente) throws SQLException {
        String consulta = "delete from cliente where idcliente like ?";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        
        sentencia.setString(1, idcliente);
        return sentencia.executeUpdate();
        
    }

    public int crearCliente(Connection conn, String idCliente, String nombre, String apellido, String direccion) throws SQLException {
       String consulta = "insert into cliente values (?,?,?,?)";
       PreparedStatement sentencia = conn.prepareStatement(consulta);
       
       sentencia.setString(1, idCliente);
       sentencia.setString(2, nombre);
       sentencia.setString(3, apellido);
       sentencia.setString(4, direccion);
       
       return sentencia.executeUpdate();
    }

    
    
    

  
}
