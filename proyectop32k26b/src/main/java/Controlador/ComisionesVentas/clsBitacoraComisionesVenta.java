/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.ComisionesVentas;
import java.time.LocalDateTime;

/**
 *
 * @author Jorge Reyes
 */
public class clsBitacoraComisionesVenta {
    private int BCVid;
    private int BCVusuarioaccion;
    private String BCVaccion;
    private int BCVtabla;
    private int BCVregistroid;
    private String BCVfecha;
    private String BCVdescripcion;

    // Constructor vacío
    public clsBitacoraComisionesVenta() {
    }

    // Constructor con parámetros
    public clsBitacoraComisionesVenta(int BCVusuarioaccion,String BCVaccion, int BCVtabla, int BCVregistroid, String BCVfecha, String BCVdescripcion){
        this.BCVusuarioaccion = BCVusuarioaccion;
        this.BCVaccion = BCVaccion;
        this.BCVtabla = BCVtabla;
        this.BCVregistroid = BCVregistroid;
        this.BCVfecha = BCVfecha;
        this.BCVdescripcion = BCVdescripcion;
    }

    // Getters y Setters

    public int getBCVid() {
        return BCVid;
    }

    public void setBCVid(int BCVid) {
        this.BCVid = BCVid;
    }

    public int getBCVusuarioaccion() {
        return BCVusuarioaccion;
    }

    public void setBCVusuarioaccion(int BCVusuarioaccion) {
        this.BCVusuarioaccion = BCVusuarioaccion;
    }

    public String getBCVaccion() {
        return BCVaccion;
    }

    public void setBCVaccion(String BCVaccion) {
        this.BCVaccion = BCVaccion;
    }

    public int getBCVtabla() {
        return BCVtabla;
    }

    public void setBCVtabla(int BCVtabla) {
        this.BCVtabla = BCVtabla;
    }

    public int getBCVregistroid() {
        return BCVregistroid;
    }

    public void setBCVregistroid(int BCVregistroid) {
        this.BCVregistroid = BCVregistroid;
    }

    public String getBCVfecha() {
        return BCVfecha;
    }

    public void setBCVfecha(String BCVfecha) {
        this.BCVfecha = BCVfecha;
    }

    public String getBCVdescripcion() {
        return BCVdescripcion;
    }

    public void setBCVdescripcion(String BCVdescripcion) {
        this.BCVdescripcion = BCVdescripcion;
    }

    @Override
    public String toString() {

        return "BitacoraComisionVenta{"
                + "BCVid=" + BCVid
                + ", idUsuario=" + BCVusuarioaccion
                + ", BCVaccion='" + BCVaccion + '\''
                + ", Aplcodigo='" + BCVtabla + '\''
                + ", BCVregistroid=" + BCVregistroid
                + ", BCVfecha='" + BCVfecha + '\''
                + ", BCVdescripcion='" + BCVdescripcion + '\''
                + '}';
    }
}

