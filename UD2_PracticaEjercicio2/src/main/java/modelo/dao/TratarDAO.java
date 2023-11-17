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
 * @author Juegos
 */
public class TratarDAO {

    public void eliminarTratamientos(Connection conn, String id_vaca) throws SQLException {
        String consulta = "delete from tratar where ID_vaca like ?";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        
        sentencia.setString(1, id_vaca);
        
        sentencia.executeUpdate();
    }

    public void insertarTratamiento(Connection conn, String idVaca, String idVeterinario, Date fecha, String tratamientos) throws SQLException {
        String consulta = "INSERT INTO Tratar (ID_vaca, ID_veterinario, fecha, tratamiento) VALUES (?,?,?,?)";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        
        sentencia.setString(1, idVaca);
        sentencia.setString(2, idVeterinario);
        sentencia.setDate(3, new java.sql.Date(fecha.getTime()));
        sentencia.setString(4, tratamientos);
     
        sentencia.executeUpdate();
    }
    
}
