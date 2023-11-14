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
import javax.swing.DefaultComboBoxModel;
import modelo.vo.*;

/**
 *
 * @author Alejandro.perezferna
 */
public class ProductoDAO {
    
     public DefaultComboBoxModel<Producto> cargarCombo(Connection conn, DefaultComboBoxModel<Producto> modeloProductos) throws SQLException {
        modeloProductos.removeAllElements();
        String consulta = "SELECT * FROM  productos";
        Statement sentencia = conn.createStatement();
        sentencia.execute(consulta);
        ResultSet rs = sentencia.getResultSet();
        
        while (rs.next()) {            
            modeloProductos.addElement(new Producto(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getFloat(4)));
        }
        return modeloProductos;
    }

    public int restarStock(Connection conn, String idProducto, Integer cantidad) throws SQLException {
        String consulta = "update productos set stock = stock - ? where idproducto like ?";
        PreparedStatement sentencia =conn.prepareStatement(consulta);
        sentencia.setInt(1, cantidad);
        sentencia.setString(2, idProducto);
        
        return sentencia.executeUpdate();
    }
     
     
     
     
     
     
     
     
}
