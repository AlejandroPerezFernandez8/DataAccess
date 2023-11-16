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
 * @author Juegos
 */
public class MataderoDAO {

    public boolean exists(Connection conn, String id_matadero) throws SQLException {
        String consulta = "SELECT * FROM matadero where ID_matadero = ?";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        
        sentencia.setString(1, id_matadero);
        ResultSet rs = sentencia.executeQuery();
        
        return rs.next();     
    }
    
}
