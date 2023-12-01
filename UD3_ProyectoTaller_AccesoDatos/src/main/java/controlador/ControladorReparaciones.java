/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import controlador.factory.HibernateUtil;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.dao.BonificacionDAO;
import modelo.dao.ClienteDAO;
import modelo.dao.CocheDAO;
import modelo.dao.EmpleadoDAO;
import modelo.dao.ReparacionDAO;
import modelo.vo.Reparacion;
import modelo.vo.ReparacionPK;
import org.hibernate.Session;
import vista.VistaReparaciones;

/**
 *
 * @author Alejandro.perezferna
 */
public class ControladorReparaciones {
    static VistaReparaciones ventana = new VistaReparaciones(null, false);
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

    public static void RepararVehiculo() {
        try {
            HibernateUtil.beginTx(session);
            //COMPROBAMOS QUE EL VEHICULO EXISTE Y QUE EL EMPLEADO EXISTE
            if (coche_dao.getCoche(session,ventana.getTxtmatricula().getText()) == null){
                JOptionPane.showMessageDialog(ventana,"El coche no existe");
                return;
            }
            if (empleado_dao.getEmpleado(session,ventana.getTxtEmpleadoID().getText().trim()) == null){
                JOptionPane.showMessageDialog(ventana,"No existe el empleado");
                return;
            }
            //COMPORBAMOS QUE NO HAYA UNA REPARCION 
            if (reparacion_dao.hasReparaciones(session,ventana.getTxtmatricula().getText())){
                JOptionPane.showMessageDialog(ventana,"El coche ya esta en una reparacion actualmente");
                return;
            }          
            //COMPROBAMOS QUE EL EMPLEADO TIENE MENOS DE 3 REPARACIONES EN CURSO
            if (!empleado_dao.puedeReparar(session,ventana.getTxtEmpleadoID().getText())){
                JOptionPane.showMessageDialog(ventana, "El empleado ya tiene el maximo de reparaciones hechas");
                return;
            }
            
            
            //HACEMOS LA INSERCION DE REPARACION
            ReparacionPK rPK = new ReparacionPK(Integer.parseInt(ventana.getTxtEmpleadoID().getText()),ventana.getTxtmatricula().getText(), Date.from(Instant.now()));
            Reparacion r = new Reparacion(rPK, Double.valueOf(ventana.getTxtCosteReparacion().getText()), null);
            
            reparacion_dao.insertar(session,r);
            JOptionPane.showMessageDialog(ventana,"Reparacion guardada");
        }catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(ventana,"Error de formato numerico");
        } catch (Exception e) {
        }finally{HibernateUtil.commitTx(session);}
    }

    
    
    
    
    
    public static void FinalizarReparacion() {
        try {
            HibernateUtil.beginTx(session);
            double coste = Double.parseDouble(ventana.getTxtCosteReparacion1().getText());
            //RECUPERAMOS LA REPARACION
            Reparacion r = reparacion_dao.getReparacionSinAcabar(session,ventana.getTxtmatricula1().getText());
            if (r == null){
                JOptionPane.showMessageDialog(ventana,"No hay reparaciones por terminar de este coche");
                return;
            }
            
            //AÑADIMOS LA FECHA y GUARDAMOS
            r.setImporte(coste);
            r.setFechaf(Date.from(Instant.now()));
            JOptionPane.showMessageDialog(ventana,"Reparacion terminada");
            
        } catch (NumberFormatException nfe){
            JOptionPane.showMessageDialog(ventana,"Error de formateo numerico");
        } catch (Exception e) {
        }finally{HibernateUtil.commitTx(session);}
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
