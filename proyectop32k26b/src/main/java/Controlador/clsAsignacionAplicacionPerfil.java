/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author giron
 */
public class clsAsignacionAplicacionPerfil {
    int AplCódigo, PerCódigo;
    private String AplpInsert,AplpSelect,AplpUpdate,AplpDelete,AplpReporte;

    public clsAsignacionAplicacionPerfil(String AplpInsert, String AplpSelect, String AplpUpdate, String AplpDelete, String AplpReporte) {
        this.AplpInsert = AplpInsert;
        this.AplpSelect = AplpSelect;
        this.AplpUpdate = AplpUpdate;
        this.AplpDelete = AplpDelete;
        this.AplpReporte = AplpReporte;
    }

    public void setAplCódigo(int AplCódigo) {
        this.AplCódigo = AplCódigo;
    }
    
    public int getAplCódigo() {
    return AplCódigo;
    }

    public void setPerCódigo(int PerCódigo) {
        this.PerCódigo = PerCódigo;
    }
    
    public int getPerCódigo() {
    return PerCódigo;
    }   

    public String getAplpInsert() {
        return AplpInsert;
    }

    public void setAplpInsert(String AplpInsert) {
        this.AplpInsert = AplpInsert;
    }

    public String getAplpSelect() {
        return AplpSelect;
    }

    public void setAplpSelect(String AplpSelect) {
        this.AplpSelect = AplpSelect;
    }

    public String getAplpUpdate() {
        return AplpUpdate;
    }

    public void setAplpUpdate(String AplpUpdate) {
        this.AplpUpdate = AplpUpdate;
    }

    public String getAplpDelete() {
        return AplpDelete;
    }

    public void setAplpDelete(String AplpDelete) {
        this.AplpDelete = AplpDelete;
    }

    public String getAplpReporte() {
        return AplpReporte;
    }

    public void setAplpReporte(String AplpReporte) {
        this.AplpReporte = AplpReporte;
    }

    public clsAsignacionAplicacionPerfil(int AplCódigo, int PerCódigo) {
        this.AplCódigo = AplCódigo;
        this.PerCódigo = PerCódigo;
    }
}
