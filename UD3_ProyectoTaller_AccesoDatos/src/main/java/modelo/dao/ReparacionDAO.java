/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.util.Iterator;
import modelo.vo.Reparacion;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Alejandro.perezferna
 */
public class ReparacionDAO {

    public void insertar(Session session, Reparacion r) {
        session.save(r);
    }
    
    public boolean hasReparaciones(Session session, String matricula) {
        String consulta = "from Reparacion r where r.reparacionPK.matricula = :m";
        Query q = session.createQuery(consulta);
        q.setParameter("m", matricula);
        Iterator it = q.list().iterator();
        
        while (it.hasNext()) {            
            Reparacion r = (Reparacion) it.next();
            if (r.getFechaf() == null){return true;}
        }
        return false;
    }

    public Reparacion getReparacionSinAcabar(Session session, String matricula) {
        String consulta = "from Reparacion r where r.reparacionPK.matricula = :m";
        Query q = session.createQuery(consulta);
        q.setParameter("m", matricula);
        Iterator it = q.list().iterator();
        
        while (it.hasNext()) {            
            Reparacion r = (Reparacion) it.next();
            if (r.getFechaf() == null){return r;}
        }
        return null;
    }
    
}
