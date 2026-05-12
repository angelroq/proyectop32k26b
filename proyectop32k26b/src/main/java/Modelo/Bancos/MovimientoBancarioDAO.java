package Modelo.Bancos;

/**
 * Data Access Object para MovimientoBancario
 * Módulo Transaccional - Código de Aplicación: 5600
 *
 * Proporciona operaciones CRUD sobre la tabla MovimientoBancario
 * y registra automáticamente cada acción en BitacoraBancaria.
 *
 * Estructura de tabla MovimientoBancario:
 *   Movbid               INT           PK AUTO_INCREMENT
 *   Movbfechamovimiento  DATETIME      DEFAULT CURRENT_TIMESTAMP
 *   Movibmonto           DECIMAL(12,2) NOT NULL
 *   Movdescripcion       VARCHAR(255)
 *   CBANid               INT           FK -> CuentaBancaria
 *   TTid                 INT           FK -> CatTipoTransaccion
 *   Movbtipomov          VARCHAR(20)   NOT NULL
 *   Movbreferencia       VARCHAR(50)
 *   Movbconciliado       CHAR(1)       DEFAULT 'N'
 *
 * @author Angel Méndez
 * @carnet 9959-24-6845
 * @since 2026-05-10
 */

import Controlador.Bancos.clsMovimientoBancario;
import Modelo.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimientoBancarioDAO {

    private static final String TABLA = "MovimientoBancario";

    // ─────────────────────────────────────────────────────────────────────
    // INSERT
    // ─────────────────────────────────────────────────────────────────────

    /**
     * Inserta un nuevo movimiento bancario.
     * La fecha se registra automáticamente por la BD (DEFAULT CURRENT_TIMESTAMP).
     *
     * @param mov   datos del movimiento
     * @param usuId usuario conectado para bitácora
     * @return filas afectadas
     */
    public int insertar(clsMovimientoBancario mov, int usuId) {
        int resultado = 0;
        String sql = "INSERT INTO MovimientoBancario "
                + "(Movibmonto, Movdescripcion, CBANid, TTid, Movbtipomov, Movbreferencia, Movbconciliado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDouble(1, mov.getMovibmonto());
            stmt.setString(2, mov.getMovdescripcion());
            stmt.setInt(3, mov.getCBANid());
            stmt.setInt(4, mov.getTTid());
            stmt.setString(5, mov.getMovbtipomov());
            stmt.setString(6, mov.getMovbreferencia());
            stmt.setString(7, mov.getMovbconciliado() != null ? mov.getMovbconciliado() : "N");
            resultado = stmt.executeUpdate();

            if (resultado > 0) {
                int nuevoId = 0;
                ResultSet gk = stmt.getGeneratedKeys();
                if (gk.next()) nuevoId = gk.getInt(1);

                String valorNuevo = "Monto=" + mov.getMovibmonto()
                        + ", Cuenta=" + mov.getCBANid()
                        + ", TipoTrans=" + mov.getTTid()
                        + ", TipoMov=" + mov.getMovbtipomov()
                        + ", Referencia=" + mov.getMovbreferencia()
                        + ", Conciliado=" + mov.getMovbconciliado();

                registrarBitacora(usuId, "INSERT", TABLA, nuevoId, null, valorNuevo,
                        "Nuevo movimiento bancario: " + mov.getMovbtipomov()
                        + " Q" + mov.getMovibmonto());
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return resultado;
    }

    /** Sobrecarga sin usuId */
    public int insertar(clsMovimientoBancario mov) { return insertar(mov, 0); }

    // ─────────────────────────────────────────────────────────────────────
    // SELECT (con JOINs)
    // ─────────────────────────────────────────────────────────────────────

    /**
     * Obtiene todos los movimientos con número de cuenta y nombre de tipo transacción.
     */
    public List<clsMovimientoBancario> seleccionar() {
        List<clsMovimientoBancario> lista = new ArrayList<>();
        String sql =
            "SELECT m.Movbid, m.Movbfechamovimiento, m.Movibmonto, m.Movdescripcion, "
            + "m.CBANid, m.TTid, m.Movbtipomov, m.Movbreferencia, m.Movbconciliado, "
            + "cb.CBANnumerocuenta, tt.TTnombretipo "
            + "FROM MovimientoBancario m "
            + "INNER JOIN CuentaBancaria cb ON m.CBANid = cb.CBANid "
            + "INNER JOIN CatTipoTransaccion tt ON m.TTid = tt.TTid "
            + "ORDER BY m.Movbid DESC";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                clsMovimientoBancario mov = new clsMovimientoBancario(
                        rs.getInt("Movbid"),
                        rs.getString("Movbfechamovimiento"),
                        rs.getDouble("Movibmonto"),
                        rs.getString("Movdescripcion"),
                        rs.getInt("CBANid"),
                        rs.getInt("TTid"),
                        rs.getString("Movbtipomov"),
                        rs.getString("Movbreferencia"),
                        rs.getString("Movbconciliado")
                );
                mov.setNumeroCuenta(rs.getString("CBANnumerocuenta"));
                mov.setNombreTipoTransaccion(rs.getString("TTnombretipo"));
                lista.add(mov);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return lista;
    }

    // ─────────────────────────────────────────────────────────────────────
    // SELECT por ID
    // ─────────────────────────────────────────────────────────────────────

    public clsMovimientoBancario buscarPorId(int id) {
        String sql =
            "SELECT m.Movbid, m.Movbfechamovimiento, m.Movibmonto, m.Movdescripcion, "
            + "m.CBANid, m.TTid, m.Movbtipomov, m.Movbreferencia, m.Movbconciliado, "
            + "cb.CBANnumerocuenta, tt.TTnombretipo "
            + "FROM MovimientoBancario m "
            + "INNER JOIN CuentaBancaria cb ON m.CBANid = cb.CBANid "
            + "INNER JOIN CatTipoTransaccion tt ON m.TTid = tt.TTid "
            + "WHERE m.Movbid = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                clsMovimientoBancario mov = new clsMovimientoBancario(
                        rs.getInt("Movbid"),
                        rs.getString("Movbfechamovimiento"),
                        rs.getDouble("Movibmonto"),
                        rs.getString("Movdescripcion"),
                        rs.getInt("CBANid"),
                        rs.getInt("TTid"),
                        rs.getString("Movbtipomov"),
                        rs.getString("Movbreferencia"),
                        rs.getString("Movbconciliado")
                );
                mov.setNumeroCuenta(rs.getString("CBANnumerocuenta"));
                mov.setNombreTipoTransaccion(rs.getString("TTnombretipo"));
                return mov;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    // ─────────────────────────────────────────────────────────────────────
    // UPDATE
    // ─────────────────────────────────────────────────────────────────────

    /**
     * Actualiza un movimiento bancario.
     * Registra valor anterior y nuevo en BitacoraBancaria.
     * Nota: No se actualiza Movbfechamovimiento (fecha queda fija al momento original).
     */
    public int actualizar(clsMovimientoBancario mov, int usuId) {
        int resultado = 0;

        clsMovimientoBancario anterior = buscarPorId(mov.getMovbid());
        String valorAnterior = anterior != null
                ? "Monto=" + anterior.getMovibmonto()
                  + ", Cuenta=" + anterior.getCBANid()
                  + ", TipoMov=" + anterior.getMovbtipomov()
                  + ", Conciliado=" + anterior.getMovbconciliado()
                : null;

        String sql = "UPDATE MovimientoBancario SET Movibmonto=?, Movdescripcion=?, "
                + "CBANid=?, TTid=?, Movbtipomov=?, Movbreferencia=?, Movbconciliado=? "
                + "WHERE Movbid=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, mov.getMovibmonto());
            stmt.setString(2, mov.getMovdescripcion());
            stmt.setInt(3, mov.getCBANid());
            stmt.setInt(4, mov.getTTid());
            stmt.setString(5, mov.getMovbtipomov());
            stmt.setString(6, mov.getMovbreferencia());
            stmt.setString(7, mov.getMovbconciliado());
            stmt.setInt(8, mov.getMovbid());
            resultado = stmt.executeUpdate();

            if (resultado > 0) {
                String valorNuevo = "Monto=" + mov.getMovibmonto()
                        + ", Cuenta=" + mov.getCBANid()
                        + ", TipoMov=" + mov.getMovbtipomov()
                        + ", Conciliado=" + mov.getMovbconciliado();
                registrarBitacora(usuId, "UPDATE", TABLA, mov.getMovbid(),
                        valorAnterior, valorNuevo,
                        "Actualización movimiento bancario ID=" + mov.getMovbid());
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return resultado;
    }

    public int actualizar(clsMovimientoBancario mov) { return actualizar(mov, 0); }

    // ─────────────────────────────────────────────────────────────────────
    // DELETE
    // ─────────────────────────────────────────────────────────────────────

    public int eliminar(int id, int usuId) {
        int resultado = 0;

        clsMovimientoBancario anterior = buscarPorId(id);
        String valorAnterior = anterior != null
                ? "Monto=" + anterior.getMovibmonto()
                  + ", TipoMov=" + anterior.getMovbtipomov()
                  + ", Cuenta=" + anterior.getCBANid()
                : null;

        String sql = "DELETE FROM MovimientoBancario WHERE Movbid=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            resultado = stmt.executeUpdate();
            if (resultado > 0) {
                registrarBitacora(usuId, "DELETE", TABLA, id,
                        valorAnterior, null,
                        "Eliminación movimiento bancario ID=" + id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return resultado;
    }

    public int eliminar(int id) { return eliminar(id, 0); }

    // ─────────────────────────────────────────────────────────────────────
    // SELECT BITÁCORA
    // ─────────────────────────────────────────────────────────────────────

    public List<Object[]> seleccionarBitacora() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT BBid, BBusuarioaccion, BBaccion, BBtabla, BBregistroid, "
                + "BBvaloranterior, BBvalornuevo, BBfechaaccion, BBdescripcion "
                + "FROM BitacoraBancaria WHERE BBtabla = ? ORDER BY BBfechaaccion DESC";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, TABLA);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("BBid"),
                    rs.getInt("BBusuarioaccion"),
                    rs.getString("BBaccion"),
                    rs.getString("BBtabla"),
                    rs.getObject("BBregistroid"),
                    rs.getString("BBvaloranterior"),
                    rs.getString("BBvalornuevo"),
                    rs.getString("BBfechaaccion"),
                    rs.getString("BBdescripcion")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return lista;
    }

    // ─────────────────────────────────────────────────────────────────────
    // HELPER: registrar en BitacoraBancaria
    // ─────────────────────────────────────────────────────────────────────

    private void registrarBitacora(int usuId, String accion, String tabla,
            int registroId, String valorAnterior, String valorNuevo, String descripcion) {

        String sql = "INSERT INTO BitacoraBancaria "
                + "(BBusuarioaccion, BBaccion, BBtabla, BBregistroid, "
                + " BBvaloranterior, BBvalornuevo, BBdescripcion) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuId);
            stmt.setString(2, accion);
            stmt.setString(3, tabla);
            stmt.setInt(4, registroId);
            stmt.setString(5, valorAnterior);
            stmt.setString(6, valorNuevo);
            stmt.setString(7, descripcion);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    public List<Object[]> seleccionarCuentas() {
    List<Object[]> lista = new ArrayList<>();
    String sql = "SELECT CBANid, CBANnumerocuenta FROM CuentaBancaria ORDER BY CBANid ASC";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next())
            lista.add(new Object[]{rs.getInt("CBANid"), rs.getString("CBANnumerocuenta")});
    } catch (SQLException ex) { ex.printStackTrace(System.out); }
    return lista;
}

public List<Object[]> seleccionarTiposTransaccion() {
    List<Object[]> lista = new ArrayList<>();
    String sql = "SELECT TTid, TTnombretipo FROM CatTipoTransaccion ORDER BY TTid ASC";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next())
            lista.add(new Object[]{rs.getInt("TTid"), rs.getString("TTnombretipo")});
    } catch (SQLException ex) { ex.printStackTrace(System.out); }
    return lista;
}

public void limpiarTabla() {
    try (Connection conn = Conexion.getConnection();
         Statement stmt = conn.createStatement()) {
        stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
        stmt.executeUpdate("TRUNCATE TABLE MovimientoBancario");
        stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
    } catch (SQLException ex) { ex.printStackTrace(System.out); }
}
}