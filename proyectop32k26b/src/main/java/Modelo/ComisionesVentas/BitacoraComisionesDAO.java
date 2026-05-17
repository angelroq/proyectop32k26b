/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.ComisionesVentas;

import Controlador.ComisionesVentas.clsBitacoraComisionesVenta;
import Modelo.Conexion;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BitacoraComisionesDAO {

    private static final String SQL_SELECT =
        "SELECT BCVid, BCVusuarioaccion, BCVaccion, BCVtabla, BCVregistroid, BCVfecha, BCVdescripcion FROM bitacoracomisionventa";

    private static final String SQL_INSERT =
        "INSERT INTO BitacoraComisionVenta(BCVusuarioaccion, BCVaccion, BCVtabla, BCVregistroid, BCVdescripcion) VALUES(?, ?, ?, ?, ?)";

    private static final String SQL_QUERY_POR_CODIGO =
        "SELECT BCVid, BCVusuarioaccion, BCVaccion, BCVtabla, BCVregistroid, BCVfecha, BCVdescripcion FROM bitacoracomisionventa WHERE BCVid=?";

    private static final String SQL_QUERY_POR_USUARIO =
        "SELECT BCVid, BCVusuarioaccion, BCVaccion, BCVtabla, BCVregistroid, BCVfecha, BCVdescripcion FROM bitacoracomisionventa WHERE BCVusuarioaccion=?";

    private static final String SQL_QUERY_POR_APLICACION =
        "SELECT BCVid, BCVusuarioaccion, BCVaccion, BCVtabla, BCVregistroid, BCVfecha, BCVdescripcion FROM bitacoracomisionventa WHERE BCVtabla=?";

    private static final String SQL_QUERY_POR_FECHAS =
        "SELECT BCVid,BCVusuarioaccion, BCVaccion, BCVtabla, BCVregistroid, BCVfecha, BCVdescripcion FROM bitacoracomisionventa WHERE BCVfecha BETWEEN ? AND ?";

    private static final String SQL_QUERY_POR_ACCION =
        "SELECT BCVid, BCVusuarioaccion, BCVaccion, BCVtabla, BCVregistroid, BCVfecha, BCVdescripcion FROM bitacoracomisionventa WHERE BCVaccion=?";

    // ---------------- FECHA ----------------
    public String fechaActual() {
        java.util.Date fecha = new java.util.Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return formatoFecha.format(fecha);
    }

    public static String horaActual() {
        java.util.Date fecha = new java.util.Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("hh:mm:ss");
        return formatoFecha.format(fecha);
    }

    private String obtenerNombrePc() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    private String obtenerIP() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    // ---------------- SELECT ----------------
    public List<clsBitacoraComisionesVenta> select() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<clsBitacoraComisionesVenta> bitacoras = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {

                clsBitacoraComisionesVenta bitacora = new clsBitacoraComisionesVenta();

                bitacora.setBCVid(rs.getInt("BCVid"));
                bitacora.setBCVusuarioaccion(rs.getInt("BCVusuarioaccion"));
                bitacora.setBCVaccion(rs.getString("BCVaccion"));
                bitacora.setBCVtabla(rs.getInt("BCVtabla"));
                bitacora.setBCVregistroid(rs.getInt("BCVregistroid"));
                bitacora.setBCVfecha(rs.getString("BCVfecha"));
                bitacora.setBCVdescripcion(rs.getString("BCVdescripcion"));

                bitacoras.add(bitacora);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return bitacoras;
    }

    // ---------------- INSERT ----------------
    public int insert(int BCVusuarioaccion,
                  String BCVaccion,
                  int BCVtabla,
                  int BCVregistroid,
                  String BCVdescripcion) {

    Connection conn = null;
    PreparedStatement stmt = null;
    int rows = 0;

    try {

        conn = Conexion.getConnection();
        stmt = conn.prepareStatement(SQL_INSERT);

        stmt.setInt(1, BCVusuarioaccion);
        stmt.setString(2, BCVaccion);
        stmt.setInt(3, BCVtabla);
        stmt.setInt(4, BCVregistroid);
        stmt.setString(5, BCVdescripcion);

        rows = stmt.executeUpdate();

        System.out.println("Bitacora insertada correctamente");

    } catch (SQLException ex) {

        ex.printStackTrace(System.out);

    } finally {

        Conexion.close(stmt);
        Conexion.close(conn);
    }

    return rows;
}

    // ---------------- POR CODIGO ----------------
    public clsBitacoraComisionesVenta queryPorCodigo(clsBitacoraComisionesVenta bitacora) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY_POR_CODIGO);

            stmt.setInt(1, bitacora.getBCVid());

            rs = stmt.executeQuery();

            while (rs.next()) {

                bitacora = new clsBitacoraComisionesVenta();

                bitacora.setBCVid(rs.getInt("BCVid"));
                bitacora.setBCVusuarioaccion(rs.getInt("BCVusuarioaccion"));
                bitacora.setBCVaccion(rs.getString("BCVaccion"));
                bitacora.setBCVtabla(rs.getInt("BCVtabla"));
                bitacora.setBCVregistroid(rs.getInt("BCVregistroid"));
                bitacora.setBCVfecha(rs.getString("BCVfecha"));
                bitacora.setBCVdescripcion(rs.getString("BCVdescripcion"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return bitacora;
    }

    // ---------------- POR USUARIO ----------------
    public List<clsBitacoraComisionesVenta> queryPorUsuario(int BCVusuarioaccion) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<clsBitacoraComisionesVenta> bitacoras = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY_POR_USUARIO);
            stmt.setInt(1, BCVusuarioaccion);

            rs = stmt.executeQuery();

            while (rs.next()) {

                clsBitacoraComisionesVenta bitacora = new clsBitacoraComisionesVenta();

                bitacora.setBCVid(rs.getInt("BCVid"));
                bitacora.setBCVusuarioaccion(rs.getInt("BCVusuarioaccion"));
                bitacora.setBCVaccion(rs.getString("BCVaccion"));
                bitacora.setBCVtabla(rs.getInt("BCVtabla"));
                bitacora.setBCVregistroid(rs.getInt("BCVregistroid"));
                bitacora.setBCVfecha(rs.getString("BCVfecha"));
                bitacora.setBCVdescripcion(rs.getString("BCVdescripcion"));

                bitacoras.add(bitacora);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return bitacoras;
    }

    // ---------------- POR APLICACION ----------------
    public List<clsBitacoraComisionesVenta> queryPorAplicacion(int BCVtabla) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<clsBitacoraComisionesVenta> bitacoras = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY_POR_APLICACION);
            stmt.setInt(1, BCVtabla);

            rs = stmt.executeQuery();

            while (rs.next()) {

                clsBitacoraComisionesVenta bitacora = new clsBitacoraComisionesVenta();

                bitacora.setBCVid(rs.getInt("BCVid"));
                bitacora.setBCVusuarioaccion(rs.getInt("BCVusuarioaccion"));
                bitacora.setBCVaccion(rs.getString("BCVaccion"));
                bitacora.setBCVtabla(rs.getInt("BCVtabla"));
                bitacora.setBCVregistroid(rs.getInt("BCVregistroid"));
                bitacora.setBCVfecha(rs.getString("BCVfecha"));
                bitacora.setBCVdescripcion(rs.getString("BCVdescripcion"));

                bitacoras.add(bitacora);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return bitacoras;
    }

    // ---------------- POR FECHAS ----------------
    public List<clsBitacoraComisionesVenta> queryPorFechas(String fechaInicio, String fechaFin) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<clsBitacoraComisionesVenta> bitacoras = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY_POR_FECHAS);

            stmt.setString(1, fechaInicio);
            stmt.setString(2, fechaFin);

            rs = stmt.executeQuery();

            while (rs.next()) {

                clsBitacoraComisionesVenta bitacora = new clsBitacoraComisionesVenta();

                bitacora.setBCVid(rs.getInt("BCVid"));
                bitacora.setBCVusuarioaccion(rs.getInt("BCVusuarioaccion"));
                bitacora.setBCVaccion(rs.getString("BCVaccion"));
                bitacora.setBCVtabla(rs.getInt("BCVtabla"));
                bitacora.setBCVregistroid(rs.getInt("BCVregistroid"));
                bitacora.setBCVfecha(rs.getString("BCVfecha"));
                bitacora.setBCVdescripcion(rs.getString("BCVdescripcion"));

                bitacoras.add(bitacora);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return bitacoras;
    }

    // ---------------- POR ACCION ----------------
    public List<clsBitacoraComisionesVenta> queryPorAccion(String BCVaccion) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<clsBitacoraComisionesVenta> bitacoras = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY_POR_ACCION);

            stmt.setString(1, BCVaccion);

            rs = stmt.executeQuery();

            while (rs.next()) {

                clsBitacoraComisionesVenta bitacora = new clsBitacoraComisionesVenta();

                bitacora.setBCVid(rs.getInt("BCVid"));
                bitacora.setBCVusuarioaccion(rs.getInt("BCVusuarioaccion"));
                bitacora.setBCVaccion(rs.getString("BCVaccion"));
                bitacora.setBCVtabla(rs.getInt("BCVtabla"));
                bitacora.setBCVregistroid(rs.getInt("BCVregistroid"));
                bitacora.setBCVfecha(rs.getString("BCVfecha"));
                bitacora.setBCVdescripcion(rs.getString("BCVdescripcion"));

                bitacoras.add(bitacora);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return bitacoras;
    }
}