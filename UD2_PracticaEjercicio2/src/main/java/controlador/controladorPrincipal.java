/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.factory.DAOFactory;
import modelo.VO.*;
import java.sql.Connection;
import java.sql.SQLException;
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
    
   
    
    
    
}
