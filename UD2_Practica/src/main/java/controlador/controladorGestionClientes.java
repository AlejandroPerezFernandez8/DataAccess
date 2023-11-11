/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import controlador.factory.DAOFactory;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.ClienteDAO;
import modelo.dao.FacturaDAO;
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
    private static final String[]columnas = {"ID Factura","ID Cliente","ID Empleado","Fecha","Cobrada"};
    static DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
    
    public static void iniciarFactory(){
        mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        cliente_dao = mysqlFactory.getClienteDAO();
        factura_dao = mysqlFactory.getFacturaDAO();
    }
    public static void cerrarFactory(){
        try {mysqlFactory.shutdown();} catch (Exception ex) {System.out.println("Error en el cierre del factory");}
    }
    public static void init(){
        ventana.getjTable1().setModel(modelo);
        ventana.setTitle("Gestion de empleados");
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
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
    public static void cargarTabla(){
        Connection conn = null;
        modelo.setRowCount(0);
        try {
            conn = mysqlFactory.getConnection();
            ArrayList<Factura> facturas =  factura_dao.obtenerFacturadeCliente(conn,ventana.getTxt_idCliente().getText());
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
    
}
