/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.VO.Veterinario;

/**
 *
 * @author Juegos
 */
public class VeterinarioDAO {

    public Veterinario getVeterinario(Connection conn, String idVeterinario) throws SQLException {
        String consulta = "SELECT * FROM veterinario where ID_veterinario = ?";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        sentencia.setString(1, idVeterinario);
        ResultSet rs = sentencia.executeQuery();
        
        if (rs.next()){
            return new Veterinario(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    String.valueOf(rs.getInt(5)));
        }
        return null;
    }
    
}
