/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.VO;

import java.util.Date;

/**
 *
 * @author Juegos
 */
public class Tratar {
    private String ID_vaca;
    private String ID_veterinario;
    private Date fecha;
    private String tratamiento;

    public Tratar(String ID_vaca, String ID_veterinario, Date fecha, String tratamiento) {
        this.ID_vaca = ID_vaca;
        this.ID_veterinario = ID_veterinario;
        this.fecha = fecha;
        this.tratamiento = tratamiento;
    }

    public String getID_vaca() {
        return ID_vaca;
    }

    public String getID_veterinario() {
        return ID_veterinario;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    @Override
    public String toString() {
        return "Tratar{" +
                "ID_vaca='" + ID_vaca + '\'' +
                ", ID_veterinario='" + ID_veterinario + '\'' +
                ", fecha=" + fecha +
                ", tratamiento='" + tratamiento + '\'' +
                '}';
    }
}
