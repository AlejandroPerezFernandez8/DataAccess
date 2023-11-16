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
 * @author Juegos
 */
public class TratarDAO {

    public void eliminarTratamientos(Connection conn, String id_vaca) throws SQLException {
        String consulta = "delete from tratar where ID_vaca like ?";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        
        sentencia.setString(1, id_vaca);
        
        sentencia.executeUpdate();
    }
    
}
