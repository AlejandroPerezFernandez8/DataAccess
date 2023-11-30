/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import org.hibernate.Session;
import vista.VistaPrincipal;
import controlador.factory.HibernateUtil;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.dao.*;
import modelo.vo.*;
/**
 *
 * @author acceso a datos
 */
public class controladorPrincipal {
    static VistaPrincipal ventana = new VistaPrincipal();
    static Session session;
    static BonificacionDAO bonificacion_dao;
    static ClienteDAO cliente_dao;
    static CocheDAO coche_dao;
    static EmpleadoDAO empleado_dao;
    static ReparacionDAO reparacion_dao;
    
    public static void init() {
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
    
    public static void IniciarSession(){
        session = HibernateUtil.getSessionFactory().openSession();
        bonificacion_dao = HibernateUtil.getBonificacionDAO();
        cliente_dao = HibernateUtil.getClienteDAO();
        coche_dao = HibernateUtil.getCocheDAO();
        empleado_dao = HibernateUtil.getEmpleadoDAO();
        reparacion_dao = HibernateUtil.getReparacionDAO();
    }
    
    public static void CerrarSession(){
        session.close();
    }
    
    private static boolean txtEmpty(JTextField txt){
        return txt.getText().isEmpty();
    }

    public static void insertarCliente() {
        try {
            HibernateUtil.beginTx(session);
            //Comprobamos que los campos no estén vacios
            if (txtEmpty(ventana.getTxtIDCliente())|| txtEmpty(ventana.getTxtNombre())|| txtEmpty(ventana.getTxtDireccion())
             || txtEmpty(ventana.getTxtEmail()) || txtEmpty(ventana.getTxtTelefono())){
                JOptionPane.showMessageDialog(ventana, "Por favor cubre todos los campos");
                return;
            }
            //Comprobamos que el cliente no existe ya
            if (cliente_dao.getCliente(session,ventana.getTxtIDCliente().getText()) != null){
                JOptionPane.showMessageDialog(ventana,"El cliente ya existe");
                return;
            }
            
            //Insertamos el cliente
            Cliente c = new Cliente(Integer.valueOf(ventana.getTxtIDCliente().getText()),
                    ventana.getTxtNombre().getText(),
                    ventana.getTxtDireccion().getText(),
                    ventana.getTxtEmail().getText(),
                    ventana.getTxtTelefono().getText());
            
            cliente_dao.insertarCLiente(session,c);
            JOptionPane.showMessageDialog(ventana, "Cliente Insertado");
        }catch (NumberFormatException nfe){
            JOptionPane.showMessageDialog(ventana, "Error de formateo numerico");
        } catch (Exception e) {
        }finally{HibernateUtil.commitTx(session);}
    }

    public static void mostrarCliente() {
        try {
            HibernateUtil.beginTx(session);
            Cliente c = cliente_dao.getCliente(session,ventana.getTxtIDCliente().getText());
            if (c != null){
                ventana.getTxtNombre().setText(c.getNomcli());
                ventana.getTxtDireccion().setText(c.getDireccion());
                ventana.getTxtEmail().setText(c.getEmail());
                ventana.getTxtTelefono().setText(c.getTfno());
            } 
        } catch (Exception e) {
        }finally{HibernateUtil.commitTx(session);}
    }

    public static void modificarEmpleado() {
        Cliente c;
        try {
            HibernateUtil.beginTx(session);
            //Comprobamos que los campos no estén vacios
            if (txtEmpty(ventana.getTxtIDCliente())|| txtEmpty(ventana.getTxtNombre())|| txtEmpty(ventana.getTxtDireccion())
             || txtEmpty(ventana.getTxtEmail()) || txtEmpty(ventana.getTxtTelefono())){
                JOptionPane.showMessageDialog(ventana, "Por favor cubre todos los campos");
                return;
            }
            //Comprobamos que el cliente existe
            if ((c = cliente_dao.getCliente(session,ventana.getTxtIDCliente().getText())) == null){
                JOptionPane.showMessageDialog(ventana,"El cliente no existe");
                return;
            }
            
            //Modificamos el cliente
             c.setNomcli(ventana.getTxtNombre().getText());
             c.setDireccion(ventana.getTxtDireccion().getText());
             c.setEmail(ventana.getTxtEmail().getText());
             c.setTfno(ventana.getTxtTelefono().getText());
             cliente_dao.modificarEmpleado(session,c);
            JOptionPane.showMessageDialog(ventana, "Cliente Modificado");
        }catch (NumberFormatException nfe){
            JOptionPane.showMessageDialog(ventana, "Error de formateo numerico");
        } catch (Exception e) {
        }finally{HibernateUtil.commitTx(session);}
    }
    
    public static void eliminarEmpleado(){
        try {
            Cliente c;
            HibernateUtil.beginTx(session);
            //COMPROBAMOS QUE LE ID NO ESTÉ VACIO
            if (txtEmpty(ventana.getTxtIDCliente())){
                JOptionPane.showMessageDialog(ventana,"Rellene el id");
                return;
            }
            //COMPROBAMOS QUE EL CLIENTE EXISTE
            if ((c = cliente_dao.getCliente(session,ventana.getTxtIDCliente().getText())) == null){
                JOptionPane.showMessageDialog(ventana,"El cliente no existe");
                return;
            }
            
            //Comprobamos que el cliente no tenga ninguna coche reparandose
            if (cliente_dao.CochesReparandose(session,ventana.getTxtIDCliente().getText())){
                JOptionPane.showMessageDialog(ventana,"El cliente tiene coches en reparacion no se puede eliminar");
                return;
            }  
           
            cliente_dao.eliminar(session,c);
            JOptionPane.showMessageDialog(ventana,"Cliente eliminado");
        } catch (Exception e) {
        }finally{HibernateUtil.commitTx(session);}
    }
    
    
    
    
    
    
    
    
    
    }
    
    
    
    
    
    

