/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.util.Iterator;

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
        String consulta = "from Cliente c inner join c.cocheList cl";
        Query q = session.createQuery(consulta);

        Iterator it = q.list().iterator();
        
        if (it.hasNext()) {   
            Object[] o = (Object[]) it.next();
            Coche c = (Coche)o[1];
            String matricula = c.getMatricula();
            String consulta2 = "from Reparacion r where r.reparacionPK.matricula =:matricula and fechaf is null";
            Query q2 = session.createQuery(consulta2);
            q2.setParameter("matricula",matricula);
            Iterator it2 = q2.list().iterator();
            while (it2.hasNext()) {                
                Reparacion r = (Reparacion)it2.next();
                if (r != null){return true;}
                System.out.println(r.toString());
            }
            
            
        }            
        return false;
    }

    public void eliminar(Session session, Cliente c) {
       session.delete(c);
    }
    
    
    
    
    
    
}
