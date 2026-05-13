//Karina Alejandra Arriaza Ortiz 9959-24-14190
//Creación y documentación

package Modelo.Bancos;

import Controlador.Bancos.clsConciliacionBancaria;
import Controlador.clsUsuarioConectado;
import Modelo.BitacoraDAO;
import Modelo.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la gestión de Conciliaciones Bancarias (ConciliacionBancaria).
 *
 * Permite realizar operaciones CRUD sobre la base de datos:
 * - Listar todos los registros
 * - Insertar nuevas conciliaciones
 * - Actualizar conciliaciones existentes
 * - Eliminar registros
 * - Consultar por ID
 * - Consultar por cuenta bancaria
 *
 * Además, cada operación es registrada en la bitácora del sistema.
 *
 * @author Karina Alejandra Arriaza Ortiz 9959-24-14190
 */
public class ConciliacionBancariaDAO {

    // Código de la aplicación para registro en bitácora
    private static final int APL_CODIGO = 5300;

    /**
     * Obtiene todas las conciliaciones bancarias registradas.
     *
     * @return Lista de objetos clsConciliacionBancaria
     */
    public List<clsConciliacionBancaria> listar() {
        List<clsConciliacionBancaria> lista = new ArrayList<>();
        String sql = "SELECT * FROM ConciliacionBancaria";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new clsConciliacionBancaria(
                    rs.getInt("Conbid"),
                    rs.getDouble("Conbsaldosistema"),
                    rs.getDouble("Conbsaldobanco"),
                    rs.getDouble("Conbdiferencia"),
                    rs.getInt("CBANid")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Inserta una nueva conciliación bancaria.
     *
     * @param conb Objeto a insertar
     */
    public void insert(clsConciliacionBancaria conb) {String sql = "INSERT INTO ConciliacionBancaria " +"(conbfecha, Conbsaldosistema, Conbsaldobanco, Conbdiferencia, CBANid, Catesid) " +"VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(conb.getConbfecha().getTime()));
            ps.setDouble(2, conb.getConbsaldosistema());
            ps.setDouble(3, conb.getConbsaldobanco());
            ps.setDouble(4, conb.getConbdiferencia());
            ps.setInt(5, conb.getCBANid());
            ps.setInt(6, conb.getCatesid());

            ps.executeUpdate();

            // Registro en bitácora
            new BitacoraDAO().insert(clsUsuarioConectado.getUsuId(), APL_CODIGO, "INSERT");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar ConciliacionBancaria", e);
        }
    }

    /**
     * Actualiza una conciliación bancaria existente.
     *
     * @param conb Objeto con los datos actualizados
     */
    public void update(clsConciliacionBancaria conb) {String sql = "UPDATE ConciliacionBancaria SET " +"conbfecha=?, Conbsaldosistema=?, Conbsaldobanco=?, " +"Conbdiferencia=?, CBANid=?, Catesid=? " +"WHERE Conbid=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(conb.getConbfecha().getTime()));
            ps.setDouble(2, conb.getConbsaldosistema());
            ps.setDouble(3, conb.getConbsaldobanco());
            ps.setDouble(4, conb.getConbdiferencia());
            ps.setInt(5, conb.getCBANid());
            ps.setInt(6, conb.getCatesid());
            ps.setInt(7, conb.getConbid());

            int rows = ps.executeUpdate();

            if (rows == 0)
                throw new RuntimeException("No se encontró la ConciliacionBancaria para actualizar");

            // Registro en bitácora
            new BitacoraDAO().insert(clsUsuarioConectado.getUsuId(), APL_CODIGO, "UPDATE");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar ConciliacionBancaria", e);
        }
    }

    /**
     * Elimina una conciliación bancaria por su ID.
     *
     * @param id Identificador de la conciliación
     */
    public void delete(int id) {
        String sql = "DELETE FROM ConciliacionBancaria WHERE Conbid=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows == 0)
                throw new RuntimeException("No se encontró la ConciliacionBancaria para eliminar");

            // Registro en bitácora
            new BitacoraDAO().insert(clsUsuarioConectado.getUsuId(), APL_CODIGO, "DELETE");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar ConciliacionBancaria", e);
        }
    }

    /**
     * Consulta una conciliación bancaria por su ID.
     *
     * @param id Identificador de la conciliación
     * @return Objeto encontrado o null si no existe
     */
    public clsConciliacionBancaria query(int id) {
        clsConciliacionBancaria conb = null;
        String sql = "SELECT * FROM ConciliacionBancaria WHERE Conbid=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    conb = new clsConciliacionBancaria(
                        rs.getInt("Conbid"),
                        rs.getDouble("Conbsaldosistema"),
                        rs.getDouble("Conbsaldobanco"),
                        rs.getDouble("Conbdiferencia"),
                        rs.getInt("CBANid"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al consultar ConciliacionBancaria", e);
        }
        return conb;
    }

    /**
     * Consulta todas las conciliaciones asociadas a una cuenta bancaria.
     *
     * @param cbanId Identificador de la cuenta bancaria
     * @return Lista de conciliaciones de esa cuenta
     */
    public List<clsConciliacionBancaria> queryPorCuenta(int cbanId) {
        List<clsConciliacionBancaria> lista = new ArrayList<>();
        String sql = "SELECT * FROM ConciliacionBancaria WHERE CBANid=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cbanId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new clsConciliacionBancaria(
                        rs.getInt("Conbid"),
                        rs.getDouble("Conbsaldosistema"),
                        rs.getDouble("Conbsaldobanco"),
                        rs.getDouble("Conbdiferencia"),
                        rs.getInt("CBANid")));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al consultar ConciliacionBancaria por cuenta", e);
        }
        return lista;
    }
}