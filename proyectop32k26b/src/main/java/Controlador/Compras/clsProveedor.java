package Controlador.Compras;

// JENNIFER BARRIOS, ENCARGADA DE PROVEEDORES.

public class clsProveedor {
    private int procodigo;
    private String pronombre;
    private String pronit;
    private String procuentabancaria;
    private String proestado;
    private String procontacto;
    private String prodepartamento;

    public clsProveedor() {
    }

    public clsProveedor(int procodigo) {
        this.procodigo = procodigo;
    }

    // Getters y Setters
    public int getProcodigo() { return procodigo; }
    public void setProcodigo(int procodigo) { this.procodigo = procodigo; }

    public String getPronombre() { return pronombre; }
    public void setPronombre(String pronombre) { this.pronombre = pronombre; }

    public String getPronit() { return pronit; }
    public void setPronit(String pronit) { this.pronit = pronit; }

    public String getProcuentabancaria() { return procuentabancaria; }
    public void setProcuentabancaria(String procuentabancaria) { this.procuentabancaria = procuentabancaria; }

    public String getProestado() { return proestado; }
    public void setProestado(String proestado) { this.proestado = proestado; }

    public String getProcontacto() { return procontacto; }
    public void setProcontacto(String procontacto) { this.procontacto = procontacto; }

    public String getProdepartamento() { return prodepartamento; }
    public void setProdepartamento(String prodepartamento) { this.prodepartamento = prodepartamento; }

    @Override
    public String toString() {
        return "clsProveedor{" + "procodigo=" + procodigo + ", pronombre=" + pronombre + ", proestado=" + proestado + '}';
    }
}