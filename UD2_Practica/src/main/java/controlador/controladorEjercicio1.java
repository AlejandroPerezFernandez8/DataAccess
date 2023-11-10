/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.factory.DAOFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    static String[] campos = {"ID","Producto","Cantidad","Precio"};
    static DefaultTableModel modeloTabla = new DefaultTableModel(campos, 0);
    
    
    // -------------INICIALIZACIONE---------------------------
    public static void init(){
        ventana.getjTablaProductos().setModel(modeloTabla);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
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

    
    //*************************************************************
    //--------------------------CONSULTAS--------------------------
    //*************************************************************
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
    public static void Facturar() {
        String num_Factura = ventana.getTxtFactura().getText();
        String id_Cliente = ventana.getTxtCliente().getText();
        String id_empleado = ((Empleado)ventana.getjComboEmpleados().getSelectedItem()).getId_empleado();
        
        Connection conn = null;
        Savepoint point = null;
        int registros;
        try {
            conn = mySQLFactory.getConnection();
            //********Primero Aumentamos la operativa del empleado********
            empleado_dao.aumentarOperativa(conn,id_empleado);
            point = conn.setSavepoint();
            
            //******************Sumamos el incentivo*************
            empleado_dao.sumarIncentivo(conn,Double.parseDouble(ventana.getTxtTotal().getText()) / 100 , id_empleado); 
            
            //**************Luego Creamos la factura*************
            factura_dao.crearFactura(conn,
                    num_Factura,
                    id_Cliente,
                    id_empleado,
                    ventana.getTxtFecha().getDate(),
                    ventana.getjCheckCobrada().isSelected(),
                    21
                    );
            
            //****************Luego Creamos el detalle******************
            //POR CADA FILA DE LA TABLA SE HACE UNA INSERCION
            for (int i = 0; i < ventana.getjTablaProductos().getRowCount(); i++) {
                
                registros = detalle_dao.crearRegistro(conn,
                        num_Factura,
                        i + 1,
                        (String)ventana.getjTablaProductos().getValueAt(i, 0),
                        Integer.parseInt(ventana.getjTablaProductos().getValueAt(i,2).toString()),
                        Float.parseFloat(ventana.getjTablaProductos().getValueAt(i,3).toString())
                        );
                System.out.println(registros);
            }
        
            conn.commit();
        }catch (SQLException sqlEX){
            try {
                switch (sqlEX.getErrorCode()) {
                    case 1062 -> {JOptionPane.showMessageDialog(ventana, "Esa factura con ese nombre ya existe");}
                }
                conn.rollback(point);
            } catch (SQLException ex) {
                Logger.getLogger(controladorEjercicio1.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("ERROR EN LA EJECUCION DE QUERYS - CODE:" + sqlEX.getErrorCode());
        } catch (Exception e) {
            System.out.println("Excepcion");
        }finally{mySQLFactory.releaseConnection(conn);}
        
        
        
        
        
    }
    
     
     
    //*************************************************************
    //----------FUNCIONES DE COMPROBACIONES DE DATOS---------------
    //*************************************************************
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
            if ( ((String) modeloTabla.getValueAt(i, 1)).equalsIgnoreCase(ventana.getjComboProductos().getSelectedItem().toString())){
               productoExiste = true;
               fila = i;
            }
        }
       
       if (productoExiste == false){
            //AÑADIR NUEVO PRODUCTO
            Producto p = (Producto)ventana.getjComboProductos().getSelectedItem();
            String[] datos = {p.getId_producto(),p.getNombre(),String.valueOf((int)ventana.getjSpinnerCantidad().getModel().getValue()),String.valueOf(p.getPrecio())};
            modeloTabla.addRow(datos);
       }else{
           int SumaTotal = Integer.parseInt(modeloTabla.getValueAt(fila, 2).toString()) + Integer.parseInt(ventana.getjSpinnerCantidad().getModel().getValue().toString());
           // ANTES DE SUMAR TENEMOS QUE MIRAR SI NOS PASAMOS DE STOCK
           if ( SumaTotal > ((Producto)ventana.getjComboProductos().getSelectedItem()).getStock()){
                JOptionPane.showMessageDialog(ventana, "La cantidad excede el stock");
           }else{
            //SUMAR CANTIDAD Y PRECIO
            modeloTabla.setValueAt(Integer.parseInt(modeloTabla.getValueAt(fila, 2).toString()) + Integer.parseInt(ventana.getjSpinnerCantidad().getModel().getValue().toString()), fila, 2);
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
            if ( ((String) modeloTabla.getValueAt(i, 1)).equalsIgnoreCase(ventana.getjComboProductos().getSelectedItem().toString())){
               productoExiste = true;
               fila = i;
            }
        }
       
       if (productoExiste == false){
            JOptionPane.showMessageDialog(ventana,"No hay ningun producto que borrar en la tabla");
            return;
       }else{
           int RestaTotal = Integer.parseInt(modeloTabla.getValueAt(fila, 2).toString()) - Integer.parseInt(ventana.getjSpinnerCantidad().getModel().getValue().toString());
           // ANTES DE SUMAR TENEMOS QUE MIRAR SI NOS PASAMOS DE STOCK
           if ( RestaTotal < 0){
                JOptionPane.showMessageDialog(ventana, "No se admiten cantidades negativas");
           }else if (RestaTotal > 0){
            //RESTAR CANTIDAD y precio
            modeloTabla.setValueAt(Integer.parseInt(modeloTabla.getValueAt(fila, 2).toString()) - Integer.parseInt(ventana.getjSpinnerCantidad().getModel().getValue().toString()), fila, 2);
           }else{
               modeloTabla.removeRow(fila);
           }
       }
    }
    public static void actualizarTotal() {
        float Total = 0;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            Total += Integer.parseInt(modeloTabla.getValueAt(i, 2).toString()) * Float.parseFloat(modeloTabla.getValueAt(i, 2).toString());
        }
        ventana.getTxtTotal().setText(String.valueOf(Total));
    }
    public static void comprobarDatos() {
        //COMPROBAMOS QUE LOS CAMPOS NO ESTEN VACIOS 
        if (ventana.getTxtFactura().getText().isEmpty()|| ventana.getTxtCliente().getText().isEmpty() ||
            ventana.getTxtFecha().getDate() == null){
            JOptionPane.showMessageDialog(ventana, "Todos los campos son obligatorios");
            return;
        }
        if (ventana.getTxtTotal().getText().isEmpty() || Float.parseFloat(ventana.getTxtTotal().getText()) == 0){
            JOptionPane.showMessageDialog(ventana, "Necesario al menos 1 articulo para realizar la factura");
            return;
        }
        
        //POR ULTIMO VOY A COMPROBAR QUE EL CLIENTE Y LA FACTURA SIGAN UNA ESTRUCTURA PARA QUE NO SE PONGA CUALQUIER DATO
        if (!ventana.getTxtFactura().getText().matches("^F\\d{3}-\\d{2}$")){
            JOptionPane.showMessageDialog(ventana, "Error: La factura debe empezar por F seguida de 3 digitos, un guion y 2 digitos");
            return;
        }
        if (!ventana.getTxtCliente().getText().matches("^C\\d{3}$")){
            JOptionPane.showMessageDialog(ventana,"Error:El cliente debe empezar por C seguido de 3 digitos");
            return;
        }
        JOptionPane.showMessageDialog(ventana, "Creando Factura.....");
    }
    
    
    
    
  
    
    
    
    
    
}
