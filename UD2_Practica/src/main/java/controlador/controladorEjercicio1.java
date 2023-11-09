/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.factory.DAOFactory;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import modelo.dao.*;
import modelo.vo.*;
import vista.Ejercicio1;

/**
 *
 * @author AD
 */
public class controladorEjercicio1 {
    static DAOFactory mySQLFactory;
    static ClienteDAO cliente_dao;
    static EmpleadoDAO empleado_dao;
    static DetalleDAO detalle_dao;
    static FacturaDAO factura_dao;
    static HistoricoDAO historico_dao;
    static ProductoDAO producto_dao;
    static Ejercicio1 ventana = new Ejercicio1();
    static DefaultComboBoxModel<Empleado> modeloEmpleados = new DefaultComboBoxModel<>();
    static DefaultComboBoxModel<Producto> modeloProducto = new DefaultComboBoxModel<>();
    static String[] campos = {"Producto","Cantidad","Precio"};
    static DefaultTableModel modeloTabla = new DefaultTableModel(campos, 0);
    
    
    // INICIAR VENTANA
    public static void init(){
        ventana.getjTablaProductos().setModel(modeloTabla);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
    
    //INICIO Y CIERRE DE FACTORY
    public static void iniciarFactory(){
        mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        cliente_dao = mySQLFactory.getClienteDAO();
        empleado_dao = mySQLFactory.getEmpleadoDAO();
        detalle_dao = mySQLFactory.getDetalleDAO();
        factura_dao = mySQLFactory.getFacturaDAO();
        historico_dao = mySQLFactory.getHistoricoDAO();
        producto_dao = mySQLFactory.getProductoAO();
    }
    
    public static void cerrarFactory(){
        try {
            mySQLFactory.shutdown();
        } catch (Exception ex) {
            System.out.println("Error en el cierre del factory");
        }
    }

    //---------------------METODOS------------------------
    
    public static void cargarComboEmpleados() {
        Connection conn = null;
        try {
            conn = mySQLFactory.getConnection();
            modeloEmpleados = empleado_dao.cargarCombo(conn,modeloEmpleados);
            ventana.getjComboEmpleados().setModel(modeloEmpleados);
        }catch (SQLException sqlEX){
            System.out.println("ERROR DE SQL:" + sqlEX.getErrorCode());
        } catch (Exception e) {
        }finally{
            mySQLFactory.releaseConnection(conn);
        }
    }
    
    
     public static void cargarComboProductos() {
        Connection conn = null;
        try {
            conn = mySQLFactory.getConnection();
            modeloProducto = producto_dao.cargarCombo(conn,modeloProducto);
            ventana.getjComboProductos().setModel(modeloProducto);
        }catch (SQLException sqlEX){
            System.out.println("ERROR DE SQL:" + sqlEX.getErrorCode());
        } catch (Exception e) {
        }finally{
            mySQLFactory.releaseConnection(conn);
        }
    }

    //PONEMOS DE LIMITE MAXIMO DEL SPINNER EL STOCK MAXIMO DEL PRODUCTO SELECCIONADO
    public static void cambiarLimiteSpinner() {
        int limite = ((Producto)ventana.getjComboProductos().getSelectedItem()).getStock();
        ventana.getjSpinnerCantidad().setModel(new SpinnerNumberModel(0, 0, limite, 1));
    }

    
    public static void añadirProductoTabla() {
       boolean productoExiste = false;
       int fila = 0;
        //SI HAY 0 UNIDADES NO SE AÑADE A LA TABLA
       if ((int)ventana.getjSpinnerCantidad().getModel().getValue() == 0){
           return;
       }
       
       //MIRAMOS SI EXISTE EL PRODUCTO EN LA TABLA
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            if ( ((String) modeloTabla.getValueAt(i, 0)).equalsIgnoreCase(ventana.getjComboProductos().getSelectedItem().toString())){
               productoExiste = true;
               fila = i;
            }
        }
       
       if (productoExiste == false){
            //AÑADIR NUEVO PRODUCTO
            Producto p = (Producto)ventana.getjComboProductos().getSelectedItem();
            String[] datos = {p.getNombre(),String.valueOf((int)ventana.getjSpinnerCantidad().getModel().getValue()),String.valueOf(p.getPrecio())};
            modeloTabla.addRow(datos);
       }else{
           int SumaTotal = Integer.parseInt(modeloTabla.getValueAt(fila, 1).toString()) + Integer.parseInt(ventana.getjSpinnerCantidad().getModel().getValue().toString());
           // ANTES DE SUMAR TENEMOS QUE MIRAR SI NOS PASAMOS DE STOCK
           if ( SumaTotal > ((Producto)ventana.getjComboProductos().getSelectedItem()).getStock()){
                JOptionPane.showMessageDialog(ventana, "La cantidad excede el stock");
           }else{
            //SUMAR CANTIDAD y precio
            modeloTabla.setValueAt(Integer.parseInt(modeloTabla.getValueAt(fila, 1).toString()) + Integer.parseInt(ventana.getjSpinnerCantidad().getModel().getValue().toString()), fila, 1);
           }
       }
    }

    public static void eliminarProductoTabla() {
       boolean productoExiste = false;
       int fila = 0;
        //SI HAY 0 UNIDADES NO SE RESTA DE LA TABLA
       if ((int)ventana.getjSpinnerCantidad().getModel().getValue() == 0){
           return;
       }
       
       //MIRAMOS SI EXISTE EL PRODUCTO EN LA TABLA
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            if ( ((String) modeloTabla.getValueAt(i, 0)).equalsIgnoreCase(ventana.getjComboProductos().getSelectedItem().toString())){
               productoExiste = true;
               fila = i;
            }
        }
       
       if (productoExiste == false){
            JOptionPane.showMessageDialog(ventana,"No hay ningun producto que borrar en la tabla");
            return;
       }else{
           int RestaTotal = Integer.parseInt(modeloTabla.getValueAt(fila, 1).toString()) - Integer.parseInt(ventana.getjSpinnerCantidad().getModel().getValue().toString());
           // ANTES DE SUMAR TENEMOS QUE MIRAR SI NOS PASAMOS DE STOCK
           if ( RestaTotal < 0){
                JOptionPane.showMessageDialog(ventana, "No se admiten cantidades negativas");
           }else if (RestaTotal > 0){
            //SUMAR CANTIDAD y precio
            modeloTabla.setValueAt(Integer.parseInt(modeloTabla.getValueAt(fila, 1).toString()) - Integer.parseInt(ventana.getjSpinnerCantidad().getModel().getValue().toString()), fila, 1);
           }else{
               modeloTabla.removeRow(fila);
           }
       }
    }

    public static void actualizarTotal() {
        float Total = 0;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            Total += Integer.parseInt(modeloTabla.getValueAt(i, 1).toString()) * Float.parseFloat(modeloTabla.getValueAt(i, 2).toString());
        }
        ventana.getTxtTotal().setText(String.valueOf(Total));
    }
    
    
    
  
    
    
    
    
    
}
