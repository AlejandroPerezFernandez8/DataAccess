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
import modelo.vo.Empleado;

/**
 *
 * @author Alejandro.perezferna
 */
public class EmpleadoDAO {

    public DefaultComboBoxModel<Empleado> cargarCombo(Connection conn, DefaultComboBoxModel<Empleado> modeloEmpleados) throws SQLException {
        modeloEmpleados.removeAllElements();
        String consulta = "SELECT * FROM  empleado";
        Statement sentencia = conn.createStatement();
        sentencia.execute(consulta);
        ResultSet rs = sentencia.getResultSet();
        
        while (rs.next()) {            
            modeloEmpleados.addElement(new Empleado(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getFloat(3),
                    rs.getFloat(4),
                    rs.getInt(5)));
        }
        return modeloEmpleados;
    }
    
}
