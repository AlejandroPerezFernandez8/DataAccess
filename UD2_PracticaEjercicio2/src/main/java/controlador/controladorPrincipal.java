/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.factory.DAOFactory;
import modelo.VO.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.dao.*;
import vista.Principal;

/**
 *
 * @author AD
 */
public class controladorPrincipal {
    static Principal ventana = new Principal();
    static ModeloTabla modelo = new ModeloTabla();
    static DAOFactory mysqlFactory;
    static VacaDAO vaca_dao;
    static MataderoDAO matadero_dao;
    static TratarDAO tratar_dao;
    static VeterinarioDAO veterinario_dao;
            
    public static void init(){
        ventana.setLocationRelativeTo(null);
        ventana.getjTable1().setModel(modelo);
        ventana.setVisible(true);
    }
    
    public static void iniciarFactory(){
        mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        vaca_dao = mysqlFactory.getVacaDAO();
        matadero_dao = mysqlFactory.getMataderoDAO();
        tratar_dao = mysqlFactory.getTratarDAO();
        veterinario_dao = mysqlFactory.getVeterinarioDAO();
    }
    
    public static void cerrarFactory(){
        try {
            mysqlFactory.shutdown();
        } catch (Exception ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    //**************************************************************************
    //******************************CONSULTAS***********************************
    //**************************************************************************
    public static void cargarTabla() {
        Connection conn = null;
        modelo.getVacas().clear();
        try {
            conn = mysqlFactory.getConnection();
            vaca_dao.getVacas(conn,modelo.getVacas());
            modelo.fireTableDataChanged();
        }catch (SQLException sqlEx){
            System.out.println(sqlEx.getErrorCode());
        } catch (Exception e) {
        }finally{mysqlFactory.releaseConnection(conn);}
        
    }

    public static void mostrarVaca() {
        Connection conn = null;
        try {
            conn = mysqlFactory.getConnection();
            
            Vaca vaca = vaca_dao.getVaca(conn,ventana.getTxtIdVaca().getText());
            
            if (vaca != null){
                ventana.getTxtIdMatadero().setText(vaca.getID_matadero());
                ventana.getTxtEdad().setText(String.valueOf(vaca.getEdad()));
                ventana.getTxtRaza().setText(vaca.getRaza());
                ventana.getTxtSexo().setText(String.valueOf(vaca.getSexo()));
            }
            
        }catch (SQLException sqlEx){
            System.out.println(sqlEx.getErrorCode());
        } catch (Exception e) {
        }finally{mysqlFactory.releaseConnection(conn);}
        
    }
    
    //----------------------------------INSERCION BORRADO Y MODIFICACION-----------------------------------

    public static void insertarVaca() {
        Connection conn = null;
        try {
            conn = mysqlFactory.getConnection();
            
            vaca_dao.InsertarVaca(
                    conn,
                    ventana.getTxtIdVaca().getText(),
                    ventana.getTxtIdMatadero().getText(),
                    ventana.getTxtEdad().getText(),
                    ventana.getTxtRaza().getText(),
                    ventana.getTxtSexo().getText()
                    );
            conn.commit();
            JOptionPane.showMessageDialog(ventana, "Vaca insertada");
        }catch (SQLException sqlEx){
            if (sqlEx.getErrorCode() == 1062){JOptionPane.showMessageDialog(ventana, "Error: La vaca ya existe");}
            System.out.println(sqlEx.getErrorCode());
        } catch (Exception e) {
        }finally{
            mysqlFactory.releaseConnection(conn);
            cargarTabla();
        }
    }

    public static void modificarVaca() {
        Connection conn = null;
        try {
            conn = mysqlFactory.getConnection();
            //PARA MODIFICAR LA VACA PRIMERO DEBEMOS COMPROBAR QUE EL NUEVO MATADERO EXISTE
            if (!matadero_dao.exists(conn,ventana.getTxtIdMatadero().getText())){
                JOptionPane.showMessageDialog(ventana, "El nuevo matadero no existe");
                return;
            }
            
            
            vaca_dao.ModificarVaca(conn,
                    ventana.getTxtIdVaca().getText(),
                    ventana.getTxtIdMatadero().getText(),
                    ventana.getTxtEdad().getText(),
                    ventana.getTxtRaza().getText(),
                    ventana.getTxtSexo().getText()
            );
            
            conn.commit();
            JOptionPane.showMessageDialog(ventana, "Vaca Modificada");
        }catch (SQLException sqlEx){
            if (sqlEx.getErrorCode() == 1062){JOptionPane.showMessageDialog(ventana, "Error: La vaca ya existe");}
            System.out.println(sqlEx.getErrorCode());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
        }finally{
            mysqlFactory.releaseConnection(conn);
            cargarTabla();
        }
    }

    public static void borrarVaca() {
        Connection conn = null;
        try {
            conn = mysqlFactory.getConnection();
            String id_vaca = ventana.getTxtIdVaca().getText();
            int vacasBorradas;
            //PARA ELIMINAR UNA VACA PRIMERO ELIMINAMOS SUS TRATAMIENTOS RELACIONADOS
            tratar_dao.eliminarTratamientos(conn,id_vaca);
            
            //AHORA ELIMINAMOS LA VACA
            vacasBorradas = vaca_dao.eliminarVaca(conn,id_vaca);
            
            conn.commit();
            JOptionPane.showMessageDialog(ventana, vacasBorradas + " Vacas Eliminadas");
        }catch (SQLException sqlEx){
            if (sqlEx.getErrorCode() == 1062){JOptionPane.showMessageDialog(ventana, "Error: La vaca ya existe");}
            System.out.println(sqlEx.getErrorCode());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
        }finally{
            mysqlFactory.releaseConnection(conn);
            cargarTabla();
        }
    }
    
   
    
    
    //--------------------------------------TRATAMIENTOS DE LA VACAS----------------------------------

    public static void realizarTratamiento() {
        Connection conn = null;
        Savepoint SP = null;
        String idVaca = ventana.getTxtIDVacaTratamientos().getText();
        String idVeterinario = ventana.getTxtIDVeterinario().getText();
        Date fecha = ventana.getjDateChooser1().getDate();
        String tratamientos = ventana.getTxtTratamiento().getText();
        
        try {
            conn = mysqlFactory.getConnection();
            //COMPROBAMOS QUE LOS CAMPOS ESTEN CUBIERTOS
            if (ventana.getTxtIDVacaTratamientos().getText().isEmpty() || ventana.getTxtIDVeterinario().getText().isEmpty() || ventana.getjDateChooser1().getDate() == null || ventana.getTxtTratamiento().getText().isEmpty()){
                JOptionPane.showMessageDialog(ventana, "Todos los datos deben estar cubiertos");
                return;
            }
            
            //VAMOS A COMPROBAR QUE LA VACA Y EL VETERINARIO EXISTEN
            if (vaca_dao.getVaca(conn,idVaca) == null){JOptionPane.showMessageDialog(ventana, "La vaca no existe");return;}
            if (veterinario_dao.getVeterinario(conn,idVeterinario) == null){JOptionPane.showMessageDialog(ventana, "El veterinario no existe");return;}
            
            
            //PARA TRATAR UNA VACA PRIMERO SUMAMOS 1 AL CAMPO 
            //TRATAMIENTOS SI O SI AUNQUE NO SE LLEGUE A REALIZAR
            vaca_dao.aumentarTratamientos(conn,idVaca);
            SP = conn.setSavepoint();

            //AHORA HACEMOS LA INSERCION EN LA TABLA TRATAR
            tratar_dao.insertarTratamiento(conn,idVaca,idVeterinario,fecha,tratamientos);
            
            
            conn.commit();
            JOptionPane.showMessageDialog(ventana,"Vaca tratada correctamente");
        }catch (SQLException sqlEx){
            if (sqlEx.getErrorCode() == 1062){JOptionPane.showMessageDialog(ventana, "Ya existe un tratamiento en la misma fecha");}
            System.out.println(sqlEx.getErrorCode());
            try {
                conn.rollback(SP);
            } catch (SQLException ex) {
                Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
        }finally{
            mysqlFactory.releaseConnection(conn);
        }
    }

    public static void CantidadVacasSinMataderoConTratamiento() {
        Connection conn = null;
        try {
            conn = mysqlFactory.getConnection();
 
            int numero = vaca_dao.Procedimiento(conn);
            ventana.getTxtNumeroVacas().setText(numero+"");
            
        }catch (SQLException sqlEx){
            System.out.println(sqlEx.getErrorCode());
        } catch (Exception e) {
        }finally{mysqlFactory.releaseConnection(conn);}
        
    }
    
    
   
    
    
    
}
