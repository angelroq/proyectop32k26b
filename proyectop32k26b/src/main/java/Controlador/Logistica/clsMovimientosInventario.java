//Ferdynand Monroy mayo 2026
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Logistica;
import java.sql.Timestamp;
/**
 *
 * @author ferito
 */
public class clsMovimientosInventario {

    private int Movimientoid;
    private int Prodid;
    private int bodegaid;
    private String Movtipomovimiento; //entradas y salidas
    private String Movmotivo; //compra, venta, merca, etc
    private int Movcantidad;
    private Timestamp Movfecha;
    private String Movtiporeferencia;
    private int Movreferenciaid;
    private String Movobservacion;
    
     // Constructor vacío
    public clsMovimientosInventario() {
    }
    
    //constructor con parametros
    public clsMovimientosInventario(int Movimientoid, int Prodid, int bodegaid, String Movtipomovimiento, String Movmotivo, int Movcantidad, Timestamp Movfecha, String Movtiporeferencia, int Movreferenciaid, String Movobservacion) {
        this.Movimientoid = Movimientoid;
        this.Prodid = Prodid;
        this.bodegaid = bodegaid;
        this.Movtipomovimiento = Movtipomovimiento;
        this.Movmotivo = Movmotivo;
        this.Movcantidad = Movcantidad;
        this.Movfecha = Movfecha;
        this.Movtiporeferencia = Movtiporeferencia;
        this.Movreferenciaid = Movreferenciaid;
        this.Movobservacion = Movobservacion;
    }
    
    //getters y setters
    public int getMovimientoid() {
        return Movimientoid;
    }

    public void setMovimientoid(int Movimientoid) {
        this.Movimientoid = Movimientoid;
    }

    public int getProdid() {
        return Prodid;
    }

    public void setProdid(int Prodid) {
        this.Prodid = Prodid;
    }

    public int getBodegaid() {
        return bodegaid;
    }

    public void setBodegaid(int bodegaid) {
        this.bodegaid = bodegaid;
    }

    public String getMovtipomovimiento() {
        return Movtipomovimiento;
    }

    public void setMovtipomovimiento(String Movtipomovimiento) {
        this.Movtipomovimiento = Movtipomovimiento;
    }

    public String getMovmotivo() {
        return Movmotivo;
    }

    public void setMovmotivo(String Movmotivo) {
        this.Movmotivo = Movmotivo;
    }

    public int getMovcantidad() {
        return Movcantidad;
    }

    public void setMovcantidad(int Movcantidad) {
        this.Movcantidad = Movcantidad;
    }

    public Timestamp getMovfecha() {
        return Movfecha;
    }

    public void setMovfecha(Timestamp Movfecha) {
        this.Movfecha = Movfecha;
    }

    public String getMovtiporeferencia() {
        return Movtiporeferencia;
    }

    public void setMovtiporeferencia(String Movtiporeferencia) {
        this.Movtiporeferencia = Movtiporeferencia;
    }

    public int getMovreferenciaid() {
        return Movreferenciaid;
    }

    public void setMovreferenciaid(int Movreferenciaid) {
        this.Movreferenciaid = Movreferenciaid;
    }

    public String getMovobservacion() {
        return Movobservacion;
    }

    public void setMovobservacion(String Movobservacion) {
        this.Movobservacion = Movobservacion;
    }

    @Override
    public String toString() {
        return "clsMovimientosInventario{" + "Movimientoid=" + Movimientoid + ", Prodid=" + Prodid + ", bodegaid=" + bodegaid + ", Movtipomovimiento=" + Movtipomovimiento + ", Movmotivo=" + Movmotivo + ", Movcantidad=" + Movcantidad + ", Movfecha=" + Movfecha + ", Movtiporeferencia=" + Movtiporeferencia + ", Movreferenciaid=" + Movreferenciaid + ", Movobservacion=" + Movobservacion + '}';
    }
    
}
