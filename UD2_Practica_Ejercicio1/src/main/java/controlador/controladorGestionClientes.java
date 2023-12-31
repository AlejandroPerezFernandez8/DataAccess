/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import controlador.factory.DAOFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import modelo.dao.ClienteDAO;
import modelo.dao.DetalleDAO;
import modelo.dao.FacturaDAO;
import modelo.dao.HistoricoDAO;
import modelo.vo.Cliente;
import modelo.vo.Factura;
import vista.GestionClientes;

/**
 *
 * @author Juegos
 */
public class controladorGestionClientes {
    static GestionClientes ventana = new GestionClientes();
    static DAOFactory mysqlFactory;
    static ClienteDAO cliente_dao;
    static FacturaDAO factura_dao;
    static DetalleDAO detalle_dao;
    static HistoricoDAO historico_dao;
    private static final String[]columnas = {"ID Factura","ID Cliente","ID Empleado","Fecha","Cobrada"};
    static DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
    
    public static void iniciarFactory(){
        mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        cliente_dao = mysqlFactory.getClienteDAO();
        factura_dao = mysqlFactory.getFacturaDAO();
        detalle_dao = mysqlFactory.getDetalleDAO();
        historico_dao = mysqlFactory.getHistoricoDAO();
    }
    public static void cerrarFactory(){
        try {mysqlFactory.shutdown();} catch (Exception ex) {System.out.println("Error en el cierre del factory");}
    }
    public static void init(){
        ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ventana.getjTable1().setModel(modelo);
        ventana.setTitle("Gestion de Clientes");
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
    
    
    
    //**************************************************
    //**********************CONSULTAS*******************
    //**************************************************

    public static void cargarTabla(){
        Connection conn = null;
        modelo.setRowCount(0);
        ArrayList<Factura> facturas; 
        
                
        if (ventana.getFechaMinima().getDate() == null || ventana.getFechaMinima().getDate() == null){
            JOptionPane.showMessageDialog(ventana,"Si desea ver las facturas rellene los campos fecha minima y fecha maxima");
            return;
        }
        
        try {
            conn = mysqlFactory.getConnection();
            
            facturas =  factura_dao.obtenerFacturadeClienteEntreFechas(
                        conn,ventana.getTxt_idCliente().getText(),
                        ventana.getFechaMinima().getDate(),
                        ventana.getFechaMaxima().getDate()
            );
            

            if (!facturas.isEmpty()){
                for (Factura factura : facturas) {
                      //SE CARGAN TODAS LAS FACTURAS
                      String[] datos = {factura.getId_factura(),factura.getId_cliente(),factura.getId_empleado(),factura.getFecha().toString(),String.valueOf(factura.isCobrada())};
                      modelo.addRow(datos);
                }     
            }
        } catch (Exception e) {
        }finally{mysqlFactory.releaseConnection(conn);}
    }
   
    public static void mostrarDatosCliente() {
        Connection conn = null;

        ventana.getTxtNombre().setText("");
        ventana.getTxtApellido().setText("");
        ventana.getTxtDireccion().setText("");
        
        try {
            conn = mysqlFactory.getConnection();
            Cliente c = cliente_dao.obtenerDatosCliente(conn,ventana.getTxt_idCliente().getText());
            
            if (c != null){
                ventana.getTxtNombre().setText(c.getNombre());
                ventana.getTxtApellido().setText(c.getApellido());
                ventana.getTxtDireccion().setText(c.getDireccion());
            }
            
        } catch (Exception e) {
        }finally{
            mysqlFactory.releaseConnection(conn);
        }
    }
   
    public static void eliminarCliente(){
        Connection conn = null;
        
        try {
            float totalFacturado;
            conn = mysqlFactory.getConnection();
            //COMPORBAMOS QUE EL CLIENTE EXISTE 
            if (cliente_dao.obtenerDatosCliente(conn,ventana.getTxt_idCliente().getText()) == null){
                JOptionPane.showMessageDialog(ventana, "El cliente no existe");
                return;
            }
            
            //COMPROBAMOS QUE EL CLIENTE TIENE TODAS LAS FACTURAS PAGADAS
            if (!cliente_dao.hasFacturasPagadas(conn,ventana.getTxt_idCliente().getText())){
                JOptionPane.showMessageDialog(ventana, "El cliente no tiene todas las facturas pagadas");
                return;
            }

            //CREAMOS EL HISTORICO DE CLIENTES
            historico_dao.crearRegistro(conn,
                    ventana.getTxt_idCliente().getText(),
                    ventana.getTxtNombre().getText(),
                    ventana.getTxtApellido().getText(),
                    getTotalFacturas(),
                    getnombreFacturas()
           );

            //ANTES DE BORRAR LAS FACTURAS BORRAMOS LOS DETTALES de todas las facturas
            detalle_dao.eliminarDetalles(conn,ventana.getTxt_idCliente().getText());
            
            //ANTES DE BORRAR EL CLIENTE TENEMOS QUE BORRAR TODAS LAS FACTURAS ASOCIADAS
            factura_dao.eliminarFacturas(conn, ventana.getTxt_idCliente().getText());
            
            //BORRAMOS EL CLIENTE
            JOptionPane.showMessageDialog(ventana, cliente_dao.eliminarCliente(conn,ventana.getTxt_idCliente().getText())+" Cliente borrado");
            
            
        }catch (SQLException sqlEX){
            System.out.println(sqlEX.getErrorCode());
            if(sqlEX.getErrorCode() == 1062){JOptionPane.showMessageDialog(ventana, "El cliente no se puede eliminar, ya existe en nuestros historicos");}
            try {
                conn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(controladorGestionClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
        }finally{
            try {conn.commit();} catch (SQLException ex) {}
            mysqlFactory.releaseConnection(conn);
        }
        
    }
    
    private static String getnombreFacturas(){
        String cadena = "";
        Connection conn = null;
        try {
            conn = mysqlFactory.getConnection();
            
            //DEBEMOS OBTNER UN ARRAYLIST CON EL ID DE FACTURA DE CADA UNA
            ArrayList<Factura> facturasCliente = factura_dao.obtenerFacturadeCliente(conn, ventana.getTxt_idCliente().getText());
            if (facturasCliente.isEmpty()){
                return cadena;
            }else{

                //AHORA HACEMOS UNA CONSULTA EN DETALLE PARA POR OBTENER EL TOTAL
                for (Factura factura : facturasCliente) {
                    cadena += factura.getId_factura()+":";
                }
            }
        }catch (SQLException sqlEX){ 
            System.out.println(sqlEX.getErrorCode());
        } catch (Exception e) {
        }finally{mysqlFactory.releaseConnection(conn);}
        
        
        return cadena;
    }
    
    private static float getTotalFacturas(){
        float total = 0;
        Connection conn = null;
        try {
            conn = mysqlFactory.getConnection();
            
            //DEBEMOS OBTNER UN ARRAYLIST CON EL ID DE FACTURA DE CADA UNA
            ArrayList<Factura> facturasCliente = factura_dao.obtenerFacturadeCliente(conn, ventana.getTxt_idCliente().getText());
            
            if (facturasCliente.isEmpty()){
                return total;
            }else{

                //AHORA HACEMOS UNA CONSULTA EN DETALLE PARA POR OBTENER EL TOTAL
                for (Factura factura : facturasCliente) {
                    total += detalle_dao.obtenerTotal(conn,factura.getId_factura());
                }
            }
        }catch (SQLException sqlEX){ 
            System.out.println(sqlEX.getErrorCode());
        } catch (Exception e) {
        }finally{mysqlFactory.releaseConnection(conn);}
        
        return total;
    }
    
    public static void cobrarFactura(){
        Connection conn = null;
        String idFactura;
        try {
            conn = mysqlFactory.getConnection();
            //COMPROBAMOS QUE LA FACTURA NO ESTE COBRADA
            if (Boolean.parseBoolean(ventana.getjTable1().getValueAt(ventana.getjTable1().getSelectedRow(),4).toString()) == true){
                JOptionPane.showMessageDialog(ventana, "La factura ya esta cobrada");
                return;
            }
            //COBRAMOS LA FACTURA
            idFactura = String.valueOf(ventana.getjTable1().getValueAt(ventana.getjTable1().getSelectedRow(), 0));
            JOptionPane.showMessageDialog(ventana,factura_dao.cobrarFactura(conn,idFactura) + " Facturas han sido cobradas");  
            
        }catch(SQLException sqlEX){
            try {
                conn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(controladorGestionClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
        }finally{
            try {conn.commit();} catch (SQLException ex) {}
            mysqlFactory.releaseConnection(conn);
            cargarTabla();
        }
        
        
        
    }

    public static void crearCliente() {
        Connection conn = null;
        int registros = 0;
        try {
            conn = mysqlFactory.getConnection();
            
            registros = cliente_dao.crearCliente(conn,
                    ventana.getTxt_idCliente().getText(),
                    ventana.getTxtNombre().getText(),
                    ventana.getTxtApellido().getText(),
                    ventana.getTxtDireccion().getText()
                    );
            JOptionPane.showMessageDialog(ventana,registros + " Clientes creados");
        }catch (SQLException sqlex){
            
            switch (sqlex.getErrorCode()) {
                case 1062 -> {JOptionPane.showMessageDialog(ventana, "El cliente ya existe");}
            }
            try {conn.rollback();} catch (SQLException ex) {System.out.println("Error durante el rolback");}
        } catch (Exception e) {
        }finally{
            try {conn.commit();} catch (SQLException ex) {}
            mysqlFactory.releaseConnection(conn);
        }
    }
    
    
}
