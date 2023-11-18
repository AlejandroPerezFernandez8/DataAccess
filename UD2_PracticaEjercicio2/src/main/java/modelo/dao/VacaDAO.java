/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JTextField;
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
        if (idMatadero == null){
            sentencia.setNull(2, Types.NULL);
        }else{
            sentencia.setString(2, idMatadero);
        }
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

    public int Procedimiento(Connection conn) throws SQLException {
        String consulta = "{call proc(?)}";
        CallableStatement sentencia = conn.prepareCall(consulta);
        sentencia.registerOutParameter(1, Types.INTEGER);
        sentencia.executeQuery();
       
        return sentencia.getInt(1);
    }

    public boolean hasTratamiento(Connection conn, String id_vaca) throws SQLException {
        String consulta = "select id_vaca from vaca where id_vaca like ? and tratamientos > 0;";
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        
        sentencia.setString(1, id_vaca);
        sentencia.executeQuery();
        
        return sentencia.getResultSet().next();
    }

    public void mostrarDatos(Connection conn,String id_vaca, JTextField txtIdVaca, JTextField txtTratamiento2, JTextField txtMatadero2, JTextField txtNombreVeterinario, JTextField txtApellidoVet) throws SQLException {
        String consulta = "SELECT " +
        "v.ID_vaca, " +
        "m.nombre AS matadero_nombre, " +
        "t.tratamiento, " +
        "vet.nombre AS veterinario_nombre, " +
        "vet.apellidos AS veterinario_apellidos " +
        "FROM " +
        "Vaca v " +
        "LEFT JOIN matadero m ON v.ID_matadero = m.ID_matadero " +
        "LEFT JOIN tratar t ON v.ID_vaca = t.ID_vaca " +
        "LEFT JOIN Veterinario vet ON t.ID_veterinario = vet.ID_veterinario " +
        "WHERE v.ID_vaca = ?";
        
        PreparedStatement sentencia = conn.prepareStatement(consulta);
        
        sentencia.setString(1, id_vaca);
        ResultSet resultado = sentencia.executeQuery();
        
        if (resultado.next()){
            txtIdVaca.setText(resultado.getString(1));
            txtMatadero2.setText(resultado.getString(2));
            txtTratamiento2.setText(resultado.getString(3));
            txtNombreVeterinario.setText(resultado.getString(4));
            txtApellidoVet.setText(resultado.getString(5));
            
        }
        
    }
    
    
    
    
    
    
    
    
    
    
}
