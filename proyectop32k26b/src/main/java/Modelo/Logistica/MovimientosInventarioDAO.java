package Modelo.Logistica;

import Modelo.Conexion;
import Controlador.Logistica.clsMovimientosInventario;
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
 * DAO encargado de gestionar las operaciones CRUD de la tabla movimientosinventario.
 * Permite insertar, actualizar, eliminar, listar y buscar registros en la base de datos.
 */
public class MovimientosInventarioDAO {

    // INSERTAR
    public boolean insertar(clsMovimientosInventario obj) {
        String sql = "INSERT INTO movimientosinventario (Prodid, bodegaid, Movtipomovimiento, Movmotivo, Movcantidad, Movfecha, Movtiporeferencia, Movreferenciaid, Movobservacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, obj.getProdid());
            ps.setInt(2, obj.getBodegaid());
            ps.setString(3, obj.getMovtipomovimiento());
            ps.setString(4, obj.getMovmotivo());
            ps.setInt(5, obj.getMovcantidad());
            ps.setTimestamp(6, obj.getMovfecha());
            ps.setString(7, obj.getMovtiporeferencia());
            ps.setInt(8, obj.getMovreferenciaid());
            ps.setString(9, obj.getMovobservacion());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ACTUALIZAR
    public boolean actualizar(clsMovimientosInventario obj) {
        String sql = "UPDATE movimientosinventario SET Prodid=?, bodegaid=?, Movtipomovimiento=?, Movmotivo=?, Movcantidad=?, Movfecha=?, Movtiporeferencia=?, Movreferenciaid=?, Movobservacion=? WHERE Movimientoid=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, obj.getProdid());
            ps.setInt(2, obj.getBodegaid());
            ps.setString(3, obj.getMovtipomovimiento());
            ps.setString(4, obj.getMovmotivo());
            ps.setInt(5, obj.getMovcantidad());
            ps.setTimestamp(6, obj.getMovfecha());
            ps.setString(7, obj.getMovtiporeferencia());
            ps.setInt(8, obj.getMovreferenciaid());
            ps.setString(9, obj.getMovobservacion());
            ps.setInt(10, obj.getMovimientoid());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminar(int id) {
        String sql = "DELETE FROM movimientosinventario WHERE Movimientoid=?";

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
    public List<clsMovimientosInventario> listar() {
        List<clsMovimientosInventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM movimientosinventario";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                clsMovimientosInventario obj = new clsMovimientosInventario();

                obj.setMovimientoid(rs.getInt("Movimientoid"));
                obj.setProdid(rs.getInt("Prodid"));
                obj.setBodegaid(rs.getInt("bodegaid"));
                obj.setMovtipomovimiento(rs.getString("Movtipomovimiento"));
                obj.setMovmotivo(rs.getString("Movmotivo"));
                obj.setMovcantidad(rs.getInt("Movcantidad"));
                obj.setMovfecha(rs.getTimestamp("Movfecha"));
                obj.setMovtiporeferencia(rs.getString("Movtiporeferencia"));
                obj.setMovreferenciaid(rs.getInt("Movreferenciaid"));
                obj.setMovobservacion(rs.getString("Movobservacion"));

                lista.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // BUSCAR POR ID
    public clsMovimientosInventario buscarPorId(int id) {
        String sql = "SELECT * FROM movimientosinventario WHERE Movimientoid=?";
        clsMovimientosInventario obj = null;

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                obj = new clsMovimientosInventario();

                obj.setMovimientoid(rs.getInt("Movimientoid"));
                obj.setProdid(rs.getInt("Prodid"));
                obj.setBodegaid(rs.getInt("bodegaid"));
                obj.setMovtipomovimiento(rs.getString("Movtipomovimiento"));
                obj.setMovmotivo(rs.getString("Movmotivo"));
                obj.setMovcantidad(rs.getInt("Movcantidad"));
                obj.setMovfecha(rs.getTimestamp("Movfecha"));
                obj.setMovtiporeferencia(rs.getString("Movtiporeferencia"));
                obj.setMovreferenciaid(rs.getInt("Movreferenciaid"));
                obj.setMovobservacion(rs.getString("Movobservacion"));
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