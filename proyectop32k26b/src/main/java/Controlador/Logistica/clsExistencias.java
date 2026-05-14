//Ferdynand Monroy mayo 2026
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Logistica;

/**
 *
 * @author ferito
 */
public class clsExistencias {
    
    private int Existenciaid;
    private int Prodid;
    private int bodegaid;
    private int Existock;
    
    //constructor vacio
    public clsExistencias(){
    }

    //constructor con parametros
    public clsExistencias(int Existenciaid, int Prodid, int bodegaid, int Existock) {
        this.Existenciaid = Existenciaid;
        this.Prodid = Prodid;
        this.bodegaid = bodegaid;
        this.Existock = Existock;
    }
    //getters y setters
    public int getExistenciaid() {
        return Existenciaid;
    }

    public void setExistenciaid(int Existenciaid) {
        this.Existenciaid = Existenciaid;
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

    public int getExistock() {
        return Existock;
    }

    public void setExistock(int Existock) {
        this.Existock = Existock;
    }

    @Override
    public String toString() {
        return "clsExistencias{" + "Existenciaid=" + Existenciaid + ", Prodid=" + Prodid + ", bodegaid=" + bodegaid + ", Existock=" + Existock + '}';
    }
    
}
