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
public class clsMarca {
    private int marcaid;
    private String marnombre;
    private int marestado;
    
    //constructor vacio
    public clsMarca(){
    
    }
    
    //constructor con parametros
    public clsMarca(int marcaid, String marnombre, int marestado) {
        this.marcaid = marcaid;
        this.marnombre = marnombre;
        this.marestado = marestado;
    }

    //getters y setters
    public int getMarcaid() {
        return marcaid;
    }

    public void setMarcaid(int marcaid) {
        this.marcaid = marcaid;
    }

    public String getMarnombre() {
        return marnombre;
    }

    public void setMarnombre(String marnombre) {
        this.marnombre = marnombre;
    }

    public int getMarestado() {
        return marestado;
    }

    public void setMarestado(int marestado) {
        this.marestado = marestado;
    }

    //to string
    @Override
    public String toString() {
        return "clsMarca{" + "marcaid=" + marcaid + ", marnombre=" + marnombre + ", marestado=" + marestado + '}';
    }
    
    
}
