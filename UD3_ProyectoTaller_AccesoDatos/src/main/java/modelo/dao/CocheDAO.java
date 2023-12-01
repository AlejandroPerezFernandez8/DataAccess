/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import org.hibernate.Session;
import modelo.vo.*;
/**
 *
 * @author Alejandro.perezferna
 */
public class CocheDAO {

    public Coche getCoche(Session session, String matricula) {
        return session.get(Coche.class, matricula);
    }

    public void insertar(Session session, Coche c) {
        session.save(c);
    }
    
}
