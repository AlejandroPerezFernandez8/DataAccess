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
import java.util.ArrayList;
import modelo.VO.Vaca;

/**
 *
 * @author Juegos
 */
public class VacaDAO {

    //DEVUELVE TODAS LAS VACAS EN UN ARRAYLIST
    public ArrayList<Vaca> getVacas(Connection conn,ArrayList<Vaca> vacas) throws SQLException {
       String consulta = "select * from vaca";
       
       Statement sentencia = conn.createStatement();
       ResultSet rs = sentencia.executeQuery(consulta);
       
        while (rs.next()) {            
            vacas.add(new Vaca(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4).charAt(0),
                    rs.getInt(5),
                    rs.getInt(6)));
        }
       return vacas;
    }

    //DEVUELVE UNA VACA SEGUN SU ID
    public Vaca getVaca(Connection conn, String Id_Vaca) throws SQLException {
        String consulta = "SELECT * FROM vacas.vaca where ID_vaca = ?";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        sentencia.setString(1, Id_Vaca);
        ResultSet rs = sentencia.executeQuery();
        
        if (rs.next()){
            return new Vaca(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4).charAt(0),
                    rs.getInt(5),
                    rs.getInt(6)
            );
        }
        return null;
    }
    
    
        //------------------INSERTAR BORRAR MODIFICAR---------------------
    
    //INSERCION DE UNA VACA
    public void InsertarVaca(Connection conn, String idVaca, String idMatadero, String Edad, String Raza, String Sexo) throws SQLException {
        String consulta = "INSERT INTO Vaca (ID_vaca, ID_matadero, raza, sexo, edad) values (?,?,?,?,?)";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        
        sentencia.setString(1, idVaca);
        sentencia.setString(2, idMatadero);
        sentencia.setString(3, Raza);
        sentencia.setString(4, Sexo);
        sentencia.setInt(5, Integer.valueOf(Edad));
        
        sentencia.executeUpdate();
    }
    
    //MODIFICACION DE UNA VACA
    public void ModificarVaca(Connection conn, String idVaca, String idMatadero, String Edad, String Raza, String Sexo) throws SQLException {
        String consulta = "update vaca set id_matadero = ?, raza = ?, sexo = ?,edad = ? where id_vaca = ?";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        
        sentencia.setString(1, idMatadero);
        sentencia.setString(2, Raza);
        sentencia.setString(3, Sexo);
        sentencia.setInt(4, Integer.valueOf(Edad));
        sentencia.setString(5, idVaca);
        
        sentencia.executeUpdate();
    }

    public int eliminarVaca(Connection conn, String id_vaca) throws SQLException {
        String consulta = "delete from vaca where ID_vaca like ?";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        
        sentencia.setString(1, id_vaca);
        
        return sentencia.executeUpdate();
    }

    
    //--------------------------Tratamientos de la vaca-------------------
    
    
    public void aumentarTratamientos(Connection conn, String idVaca) throws SQLException {
        String consulta = "update vaca set tratamientos = tratamientos + 1 where id_vaca = ?";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        
        sentencia.setString(1, idVaca);
        sentencia.executeUpdate();
    }

   
        
    
    
    
    
    
    
    
}
