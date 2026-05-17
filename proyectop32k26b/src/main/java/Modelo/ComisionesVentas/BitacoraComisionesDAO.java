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
        "SELECT BCVid, BCVusuarioaccion, BCVaccion, BCVtabla, BCVregistroid, BCVfecha, BCVdescripcion FROM BitacoraComisionVenta ORDER BY BCVid DESC";

    private static final String SQL_INSERT =
        "INSERT INTO BitacoraComisionVenta(BCVusuarioaccion, BCVaccion, BCVtabla, BCVregistroid, BCVdescripcion) VALUES(?, ?, ?, ?, ?)";

    private static final String SQL_QUERY_POR_CODIGO =
        "SELECT BCVid, BCVusuarioaccion, BCVaccion, BCVtabla, BCVregistroid, BCVfecha, BCVdescripcion FROM BitacoraComisionVenta WHERE BCVid=?";

    private static final String SQL_QUERY_POR_USUARIO =
        "SELECT BCVid, BCVusuarioaccion, BCVaccion, BCVtabla, BCVregistroid, BCVfecha, BCVdescripcion FROM BitacoraComisionVenta WHERE BCVusuarioaccion=? ORDER BY BCVid DESC";

    private static final String SQL_QUERY_POR_APLICACION =
        "SELECT BCVid, BCVusuarioaccion, BCVaccion, BCVtabla, BCVregistroid, BCVfecha, BCVdescripcion FROM BitacoraComisionVenta WHERE BCVtabla=? ORDER BY BCVid DESC";

    private static final String SQL_QUERY_POR_FECHAS =
        "SELECT BCVid, BCVusuarioaccion, BCVaccion, BCVtabla, BCVregistroid, BCVfecha, BCVdescripcion FROM BitacoraComisionVenta WHERE DATE(BCVfecha) BETWEEN ? AND ? ORDER BY BCVid DESC";

    private static final String SQL_QUERY_POR_ACCION =
        "SELECT BCVid, BCVusuarioaccion, BCVaccion, BCVtabla, BCVregistroid, BCVfecha, BCVdescripcion FROM BitacoraComisionVenta WHERE BCVaccion=? ORDER BY BCVid DESC";

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
public List<clsBitacoraComisionesVenta> select() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Lista donde se guardarán los registros obtenidos
        List<clsBitacoraComisionesVenta> bitacoras = new ArrayList<>();

        try {

            // Obtener conexión
            conn = Conexion.getConnection();

            // Preparar consulta
            stmt = conn.prepareStatement(SQL_SELECT);

            // Ejecutar consulta
            rs = stmt.executeQuery();

            // Recorrer resultados
            while (rs.next()) {

                // Crear objeto de bitácora
                clsBitacoraComisionesVenta bitacora = new clsBitacoraComisionesVenta();

                // Asignar valores obtenidos de la BD
                bitacora.setBCVid(rs.getInt("BCVid"));
                bitacora.setBCVusuarioaccion(rs.getInt("BCVusuarioaccion"));
                bitacora.setBCVaccion(rs.getString("BCVaccion"));
                bitacora.setBCVtabla(rs.getInt("BCVtabla"));
                bitacora.setBCVregistroid(rs.getInt("BCVregistroid"));
                bitacora.setBCVfecha(rs.getString("BCVfecha"));
                bitacora.setBCVdescripcion(rs.getString("BCVdescripcion"));

                // Agregar objeto a la lista
                bitacoras.add(bitacora);
            }

        } catch (SQLException ex) {

            ex.printStackTrace(System.out);

        } finally {

            // Cerrar conexiones
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return bitacoras;
    }

    /**
     * Método para insertar registros en la bitácora
     *
     * @param BCVusuarioaccion Usuario que realizó la acción
     * @param BCVaccion Acción realizada
     * @param BCVtabla Código de la aplicación
     * @param BCVregistroid ID del registro afectado
     * @param BCVdescripcion Descripción de la acción
     * @return Cantidad de filas afectadas
     */
    public int insert(int BCVusuarioaccion,
                      String BCVaccion,
                      int BCVtabla,
                      int BCVregistroid,
                      String BCVdescripcion) {

        Connection conn = null;
        PreparedStatement stmt = null;

        int rows = 0;

        try {

            // Obtener conexión
            conn = Conexion.getConnection();

            // Preparar consulta
            stmt = conn.prepareStatement(SQL_INSERT);

            // Asignar parámetros
            stmt.setInt(1, BCVusuarioaccion);
            stmt.setString(2, BCVaccion);
            stmt.setInt(3, BCVtabla);
            stmt.setInt(4, BCVregistroid);
            stmt.setString(5, BCVdescripcion);

            // Ejecutar INSERT
            rows = stmt.executeUpdate();

            System.out.println("Bitácora insertada correctamente");

        } catch (SQLException ex) {

            ex.printStackTrace(System.out);

        } finally {

            // Cerrar conexiones
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    /**
     * Método para buscar un registro por código
     *
     * @param bitacora Objeto con el código a buscar
     * @return Registro encontrado o null
     */
    public clsBitacoraComisionesVenta queryPorCodigo(clsBitacoraComisionesVenta bitacora) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        clsBitacoraComisionesVenta resultado = null;

        try {

            conn = Conexion.getConnection();

            stmt = conn.prepareStatement(SQL_QUERY_POR_CODIGO);

            stmt.setInt(1, bitacora.getBCVid());

            rs = stmt.executeQuery();

            // Si encuentra resultados
            if (rs.next()) {

                resultado = new clsBitacoraComisionesVenta();

                resultado.setBCVid(rs.getInt("BCVid"));
                resultado.setBCVusuarioaccion(rs.getInt("BCVusuarioaccion"));
                resultado.setBCVaccion(rs.getString("BCVaccion"));
                resultado.setBCVtabla(rs.getInt("BCVtabla"));
                resultado.setBCVregistroid(rs.getInt("BCVregistroid"));
                resultado.setBCVfecha(rs.getString("BCVfecha"));
                resultado.setBCVdescripcion(rs.getString("BCVdescripcion"));
            }

        } catch (SQLException ex) {

            ex.printStackTrace(System.out);

        } finally {

            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return resultado;
    }
}
    