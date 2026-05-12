package Controlador.Bancos;

/**
 * Clase Controlador para MovimientoBancario
 * Módulo Transaccional - Código de Aplicación: 5600
 *
 * Representa un movimiento bancario (depósito, retiro, etc.)
 * asociado a una cuenta bancaria.
 *
 * Estructura de tabla MovimientoBancario:
 *   Movbid               INT           PK AUTO_INCREMENT
 *   Movbfechamovimiento  DATETIME      DEFAULT CURRENT_TIMESTAMP
 *   Movibmonto           DECIMAL(12,2) NOT NULL
 *   Movdescripcion       VARCHAR(255)
 *   CBANid               INT           FK -> CuentaBancaria
 *   TTid                 INT           FK -> CatTipoTransaccion
 *   Movbtipomov          VARCHAR(20)   NOT NULL  (ej: "Credito","Debito")
 *   Movbreferencia       VARCHAR(50)
 *   Movbconciliado       CHAR(1)       DEFAULT 'N'
 *
 * @author Angel Méndez
 * @carnet 9959-24-6845
 * @since 2026-05-10
 */

import Modelo.Bancos.MovimientoBancarioDAO;
import java.util.List;

public class clsMovimientoBancario {

    // ── Atributos ─────────────────────────────────────────────────────────
    private int    Movbid;
    private String Movbfechamovimiento;
    private double Movibmonto;
    private String Movdescripcion;
    private int    CBANid;
    private int    TTid;
    private String Movbtipomov;
    private String Movbreferencia;
    private String Movbconciliado;

    // Campos auxiliares para mostrar en tabla
    private String numeroCuenta;
    private String nombreTipoTransaccion;

    // ── Constructores ─────────────────────────────────────────────────────

    public clsMovimientoBancario() {}
    public List<Object[]> getCuentasBancarias() { return new MovimientoBancarioDAO().seleccionarCuentas(); }
    public List<Object[]> getTiposTransaccion() { return new MovimientoBancarioDAO().seleccionarTiposTransaccion(); }
    public void limpiarTabla() { new MovimientoBancarioDAO().limpiarTabla(); }

    /**
     * Constructor completo
     */
    public clsMovimientoBancario(int Movbid, String Movbfechamovimiento,
            double Movibmonto, String Movdescripcion,
            int CBANid, int TTid, String Movbtipomov,
            String Movbreferencia, String Movbconciliado) {
        this.Movbid              = Movbid;
        this.Movbfechamovimiento = Movbfechamovimiento;
        this.Movibmonto          = Movibmonto;
        this.Movdescripcion      = Movdescripcion;
        this.CBANid              = CBANid;
        this.TTid                = TTid;
        this.Movbtipomov         = Movbtipomov;
        this.Movbreferencia      = Movbreferencia;
        this.Movbconciliado      = Movbconciliado;
    }

    // ── Getters y Setters ─────────────────────────────────────────────────

    public int getMovbid() { return Movbid; }
    public void setMovbid(int Movbid) { this.Movbid = Movbid; }

    public String getMovbfechamovimiento() { return Movbfechamovimiento; }
    public void setMovbfechamovimiento(String Movbfechamovimiento) { this.Movbfechamovimiento = Movbfechamovimiento; }

    public double getMovibmonto() { return Movibmonto; }
    public void setMovibmonto(double Movibmonto) { this.Movibmonto = Movibmonto; }

    public String getMovdescripcion() { return Movdescripcion; }
    public void setMovdescripcion(String Movdescripcion) { this.Movdescripcion = Movdescripcion; }

    public int getCBANid() { return CBANid; }
    public void setCBANid(int CBANid) { this.CBANid = CBANid; }

    public int getTTid() { return TTid; }
    public void setTTid(int TTid) { this.TTid = TTid; }

    public String getMovbtipomov() { return Movbtipomov; }
    public void setMovbtipomov(String Movbtipomov) { this.Movbtipomov = Movbtipomov; }

    public String getMovbreferencia() { return Movbreferencia; }
    public void setMovbreferencia(String Movbreferencia) { this.Movbreferencia = Movbreferencia; }

    public String getMovbconciliado() { return Movbconciliado; }
    public void setMovbconciliado(String Movbconciliado) { this.Movbconciliado = Movbconciliado; }

    // Auxiliares
    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public String getNombreTipoTransaccion() { return nombreTipoTransaccion; }
    public void setNombreTipoTransaccion(String nombreTipoTransaccion) { this.nombreTipoTransaccion = nombreTipoTransaccion; }

    // ── Métodos de Negocio ────────────────────────────────────────────────

    public int setInsertar(clsMovimientoBancario mov) {
        return new MovimientoBancarioDAO().insertar(mov);
    }

    public List<clsMovimientoBancario> getListado() {
        return new MovimientoBancarioDAO().seleccionar();
    }

    public clsMovimientoBancario getBuscarPorId(int id) {
        return new MovimientoBancarioDAO().buscarPorId(id);
    }

    public int setActualizar(clsMovimientoBancario mov) {
        return new MovimientoBancarioDAO().actualizar(mov);
    }

    public int setEliminar(int id) {
        return new MovimientoBancarioDAO().eliminar(id);
    }

    @Override
    public String toString() {
        return "clsMovimientoBancario{Movbid=" + Movbid
                + ", Movibmonto=" + Movibmonto + "}";
    }
}
