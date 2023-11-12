/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import modelo.vo.Factura;

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

    public ArrayList<Factura> obtenerFacturadeCliente(Connection conn, String id_cliente) throws SQLException {
        ArrayList<Factura> facturas = new ArrayList<>();
        String consulta = "SELECT * FROM factura where idcliente like ?";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        sentencia.setString(1, id_cliente);
        ResultSet resultado = sentencia.executeQuery();
        
        while (resultado.next()) {            
            facturas.add(new Factura(
                    resultado.getString(1),
                    resultado.getString(2), 
                    resultado.getString(3),
                    resultado.getDate(4),
                    resultado.getBoolean(5)));
        }
        return facturas;
    }

    public int cobrarFactura(Connection conn, String idFactura) throws SQLException {
       String consulta = "update factura set cobrada = 1 where numfactura = ?";
       PreparedStatement sentencia = conn.prepareStatement(consulta);
       sentencia.setString(1, idFactura);
       
       return sentencia.executeUpdate();
    }
    
}
