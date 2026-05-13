/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.ComisionesVentas;

/**
 *
 * @author Xander Reyes
 */
public class clsReportes {
    
    private int Venid;
    private String ven_nombre;
    private double Comcomision;
    private String Comfecha;

    // Constructor vacío
    public clsReportes() {
    }

    // Constructor con parámetros
    public clsReportes(int Venid, String ven_nombre, double Comcomision, String Comfecha) {
        this.Venid = Venid;
        this.ven_nombre = ven_nombre;
        this.Comcomision = Comcomision;
        this.Comfecha = Comfecha;
        
    }

    // GETTERS Y SETTERS
    public int getVenid() {
        return Venid;
    }

    public void setVenid(int Venid) {
        this.Venid = Venid;
    }

    public String getVen_nombre() {
        return ven_nombre;
    }

    public void setVen_nombre(String ven_nombre) {
        this.ven_nombre = ven_nombre;
    }

    public double getComcomision() {
        return Comcomision;
    }

    public void setComcomision(double Comcomision) {
        this.Comcomision = Comcomision;
    }
    
    public String getComfecha() {
        return Comfecha;
    }
    
    public void setComfecha (String Comfecha) {
        this.Comfecha = Comfecha;
    }

    @Override
    public String toString() {
        return "clsReportes{" +
                "Venid=" + Venid +
                ", ven_nombre='" + ven_nombre + '\'' +
                ", Comcomision=" + Comcomision +
                ", Comfecha=" + Comfecha + '\'' +
                '}';
    }

}
