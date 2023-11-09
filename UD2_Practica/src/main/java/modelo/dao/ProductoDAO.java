/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.Connection;
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
    
     public DefaultComboBoxModel<Producto> cargarCombo(Connection conn, DefaultComboBoxModel<Producto> modeloEmpleados) throws SQLException {
        modeloEmpleados.removeAllElements();
        String consulta = "SELECT * FROM  productos";
        Statement sentencia = conn.createStatement();
        sentencia.execute(consulta);
        ResultSet rs = sentencia.getResultSet();
        
        while (rs.next()) {            
            modeloEmpleados.addElement(new Producto(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getFloat(4)));
        }
        return modeloEmpleados;
    }
     
     
     
     
     
     
     
     
}
