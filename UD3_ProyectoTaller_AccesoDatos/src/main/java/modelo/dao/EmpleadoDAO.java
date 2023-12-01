/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.util.Iterator;
import org.hibernate.Session;
import modelo.vo.*;
import org.hibernate.query.Query;
/**
 *
 * @author Alejandro.perezferna
 */
public class EmpleadoDAO {

    public Empleado getEmpleado(Session session, String id) {
        String consulta = "from Empleado e where e.id = :id";
        Query q = session.createQuery(consulta);
        q.setParameter("id", id);
        Iterator it = q.list().iterator();
        if (it.hasNext()){return (Empleado) it.next();}
        
        return null;
    }

    public boolean puedeReparar(Session session, String idEmpleado) {
        String consulta = " select count(*) from Reparacion r where r.empleado.id like :cod and r.fechaf is null";
        
        Query q = session.createQuery(consulta);
        q.setParameter("cod",idEmpleado );
        Iterator it = q.list().iterator();
        long cochesReparandose = (long) it.next();
        if (cochesReparandose >= 3){
            return false;
        }
        return true;
    }
    
}
