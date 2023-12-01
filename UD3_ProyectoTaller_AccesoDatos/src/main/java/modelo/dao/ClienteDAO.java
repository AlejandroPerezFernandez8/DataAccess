/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTable;

import modelo.vo.Cliente;
import modelo.vo.Coche;
import modelo.vo.Reparacion;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Alejandro.perezferna
 */
public class ClienteDAO {

    public Cliente getCliente(Session session, String id) {
        String consulta = "from Cliente c where c.id = :id";
        
        Query q = session.createQuery(consulta);
        q.setParameter("id", id);
        
        
        Iterator it = q.list().iterator();
        if (it.hasNext()){return ((Cliente)it.next());}
        return null;
    }

    public void insertarCLiente(Session session, Cliente c) {
        session.save(c);
    }

    public void modificarEmpleado(Session session, Cliente c) {
        session.save(c);
    }

    public boolean CochesReparandose(Session session, String id) {
        String consulta = "from Coche co inner join co.reparacionList where co.cliente.id = :id";
        Query q = session.createQuery(consulta);
        q.setParameter("id", id);
        Iterator it = q.list().iterator();
        
        while (it.hasNext()) {            
            Object[] res = (Object[]) it.next();
            Reparacion r = (Reparacion)res[1];
            if (r.getFechaf() == null){return true;}
        }
        return false;
    }

    public void eliminar(Session session, Cliente c) {
       session.delete(c);
    }

    public void getDatos(Session session, JTable jTable1) {
        String consulta = "select c.id,c.nomcli,co.matricula,co.marca,co.modelo from Cliente c inner join c.cocheList co";
        Query q = session.createQuery(consulta);
        Iterator it = q.list().iterator();
        
        while (it.hasNext()) {            
            String[] datos = (String[]) it.next();
            
            for (int i = 0; i < datos.length; i++) {
                System.out.println(datos[i]);
            }
            
            jTable1.setValueAt(datos[0], jTable1.getRowCount(), 1);
            jTable1.setValueAt(datos[1], jTable1.getRowCount(), 2);
            jTable1.setValueAt(datos[2], jTable1.getRowCount(), 3); 
            jTable1.setValueAt(datos[3], jTable1.getRowCount(), 4); 
            jTable1.setValueAt(datos[4], jTable1.getRowCount(), 5); 
        }
        
        
        
        
    }
    
    
    
    
    
    
}
