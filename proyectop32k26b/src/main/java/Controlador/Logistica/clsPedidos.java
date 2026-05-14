//Ferdynan Monroy abril 2026
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
public class clsPedidos {
    
    private int Pedid;
    private int Cliid;
    private Timestamp Pedfecha;
    private String Pedestado;
    
    //constructor vacio
    public clsPedidos(){
    }

    //constructor vacio
    public clsPedidos(int Pedid, int Cliid, Timestamp Pedfecha, String Pedestado) {
        this.Pedid = Pedid;
        this.Cliid = Cliid;
        this.Pedfecha = Pedfecha;
        this.Pedestado = Pedestado;
    }
    //getters y setters
    public int getPedid() {
        return Pedid;
    }

    public void setPedid(int Pedid) {
        this.Pedid = Pedid;
    }

    public int getCliid() {
        return Cliid;
    }

    public void setCliid(int Cliid) {
        this.Cliid = Cliid;
    }

    public Timestamp getPedfecha() {
        return Pedfecha;
    }

    public void setPedfecha(Timestamp Pedfecha) {
        this.Pedfecha = Pedfecha;
    }

    public String getPedestado() {
        return Pedestado;
    }

    public void setPedestado(String Pedestado) {
        this.Pedestado = Pedestado;
    }

    @Override
    public String toString() {
        return "clsPedidos{" + "Pedid=" + Pedid + ", Cliid=" + Cliid + ", Pedfecha=" + Pedfecha + ", Pedestado=" + Pedestado + '}';
    }

}
