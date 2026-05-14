package Modelo.Logistica;

import Modelo.Conexion;
import Controlador.Logistica.clsPedidos;
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
 * DAO encargado de gestionar las operaciones CRUD de la tabla pedidos.
 * Permite insertar, actualizar, eliminar, listar y buscar registros en la base de datos.
 */
public class PedidosDAO {

    // INSERTAR
    public boolean insertar(clsPedidos obj) {
        String sql = "INSERT INTO pedidos (Cliid, Pedfecha, Pedestado) VALUES (?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, obj.getCliid());
            ps.setTimestamp(2, obj.getPedfecha());
            ps.setString(3, obj.getPedestado());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ACTUALIZAR
    public boolean actualizar(clsPedidos obj) {
        String sql = "UPDATE pedidos SET Cliid=?, Pedfecha=?, Pedestado=? WHERE Pedid=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, obj.getCliid());
            ps.setTimestamp(2, obj.getPedfecha());
            ps.setString(3, obj.getPedestado());
            ps.setInt(4, obj.getPedid());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminar(int id) {
        String sql = "DELETE FROM pedidos WHERE Pedid=?";

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
    public List<clsPedidos> listar() {
        List<clsPedidos> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                clsPedidos obj = new clsPedidos();

                obj.setPedid(rs.getInt("Pedid"));
                obj.setCliid(rs.getInt("Cliid"));
                obj.setPedfecha(rs.getTimestamp("Pedfecha"));
                obj.setPedestado(rs.getString("Pedestado"));

                lista.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // BUSCAR POR ID
    public clsPedidos buscarPorId(int id) {
        String sql = "SELECT * FROM pedidos WHERE Pedid=?";
        clsPedidos obj = null;

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                obj = new clsPedidos();

                obj.setPedid(rs.getInt("Pedid"));
                obj.setCliid(rs.getInt("Cliid"));
                obj.setPedfecha(rs.getTimestamp("Pedfecha"));
                obj.setPedestado(rs.getString("Pedestado"));
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