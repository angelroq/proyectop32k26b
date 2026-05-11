/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.ComisionesVentas;

/**
 *
 * @author giron
 */
public class clsComisionVentas {
     private int id_comision;
    private int ven_id;
    private int id_empleado;
    private double monto_ventas;
    private double meta;
    private double ventas_adicionales;
    private double comision;

    public clsComisionVentas (){
        
    }
    public clsComisionVentas(int id_comision, int ven_id, int id_empleado, double monto_ventas, double meta, double ventas_adicionales, double comision) {
        this.id_comision = id_comision;
        this.ven_id = ven_id;
        this.id_empleado = id_empleado;
        this.monto_ventas = monto_ventas;
        this.meta = meta;
        this.ventas_adicionales = ventas_adicionales;
        this.comision = comision;
    }

    public int getId_comision() {
        return id_comision;
    }

    public void setId_comision(int id_comision) {
        this.id_comision = id_comision;
    }

    public int getVen_id() {
        return ven_id;
    }

    public void setVen_id(int ven_id) {
        this.ven_id = ven_id;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public double getMonto_ventas() {
        return monto_ventas;
    }

    public void setMonto_ventas(double monto_ventas) {
        this.monto_ventas = monto_ventas;
    }

    public double getMeta() {
        return meta;
    }

    public void setMeta(double meta) {
        this.meta = meta;
    }

    public double getVentas_adicionales() {
        return ventas_adicionales;
    }

    public void setVentas_adicionales(double ventas_adicionales) {
        this.ventas_adicionales = ventas_adicionales;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    @Override
    public String toString() {
        return "clsComisionVentas{" + "id_comision=" + id_comision + ", ven_id=" + ven_id + ", id_empleado=" + id_empleado + ", monto_ventas=" + monto_ventas + ", meta=" + meta + ", ventas_adicionales=" + ventas_adicionales + ", comision=" + comision + '}';
    }
}
