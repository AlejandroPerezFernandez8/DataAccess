/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import vista.MenuPrincipal;

/**
 *
 * @author Juegos
 */
public class controladorMenuPrincipal {
    static MenuPrincipal ventana = new MenuPrincipal();
    
    public static void init(){
        ventana.setTitle("ventanaPrincipal");
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
    
    public static void initCrearFacturas(){
        controladorGestionFacturas.init();
    }
    
    
}
