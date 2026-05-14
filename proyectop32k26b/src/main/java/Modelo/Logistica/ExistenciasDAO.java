package Modelo.Logistica;

import Modelo.Conexion;
import Controlador.Logistica.clsExistencias;
import Controlador.clsUsuarioConectado;
import Modelo.BitacoraDAO;
import java.sql.*;
import java.util.*;

/**
 * Autor: Anthony Hetzael Suc Gomez
 * Carné: 9959-24-389
 * Fecha de creación: 2026
 * 
 * Descripción:
 * DAO encargado de gestionar las operaciones CRUD de la tabla existencias.
 * Permite insertar, actualizar, eliminar, listar y buscar registros en la base de datos.
 */
public class ExistenciasDAO {

    // INSERTAR
    public boolean insertar(clsExistencias obj) {
        String sql = "INSERT INTO existencias (Prodid, bodegaid, Existock) VALUES (?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, obj.getProdid());
            ps.setInt(2, obj.getBodegaid());
            ps.setInt(3, obj.getExistock());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ACTUALIZAR
    public boolean actualizar(clsExistencias obj) {
        String sql = "UPDATE existencias SET Prodid=?, bodegaid=?, Existock=? WHERE Existenciaid=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, obj.getProdid());
            ps.setInt(2, obj.getBodegaid());
            ps.setInt(3, obj.getExistock());
            ps.setInt(4, obj.getExistenciaid());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminar(int id) {
        String sql = "DELETE FROM existencias WHERE Existenciaid=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // LISTAR
    public List<clsExistencias> listar() {
        List<clsExistencias> lista = new ArrayList<>();
        String sql = "SELECT * FROM existencias";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                clsExistencias obj = new clsExistencias();

                obj.setExistenciaid(rs.getInt("Existenciaid"));
                obj.setProdid(rs.getInt("Prodid"));
                obj.setBodegaid(rs.getInt("bodegaid"));
                obj.setExistock(rs.getInt("Existock"));

                lista.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // BUSCAR POR ID
    public clsExistencias buscarPorId(int id) {
        String sql = "SELECT * FROM existencias WHERE Existenciaid=?";
        clsExistencias obj = null;

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                obj = new clsExistencias();

                obj.setExistenciaid(rs.getInt("Existenciaid"));
                obj.setProdid(rs.getInt("Prodid"));
                obj.setBodegaid(rs.getInt("bodegaid"));
                obj.setExistock(rs.getInt("Existock"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }
    /**
     * Registra una acción en la bitácora del sistema.
     * 
     * @param accion Descripción de la acción realizada
     */
    private void registrarBitacora(String accion) {

        int usuario = clsUsuarioConectado.getUsuId();

        // Validación de usuario autenticado
        if (usuario == 0) {
            throw new RuntimeException("No hay usuario autenticado");
        }

        BitacoraDAO bitacora = new BitacoraDAO();

        // ID de aplicación para bitácora (debe existir en la BD)
        int aplCodigoBitacora = 2000;

        bitacora.insert(usuario, aplCodigoBitacora, accion);
    }
}