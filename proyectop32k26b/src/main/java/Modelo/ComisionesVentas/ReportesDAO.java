/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.ComisionesVentas;
import Controlador.ComisionesVentas.clsReportes;
import Modelo.BitacoraDAO;
import Controlador.clsUsuarioConectado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.Conexion;
import javax.swing.JOptionPane;
/**
 *
 * @author xander reyes
 */
public class ReportesDAO {

     /**
 * Esta clase maneja toda la comunicación entre la aplicación y la base de datos
 * para el módulo de comisiones y reportes.
 */

    // Método para obtener el nombre de un vendedor buscando por su ID único
    public String obtenerNombreVendedor(int id) {
        String nombre = ""; 
        String sql = "SELECT Vennombre FROM Vendedores WHERE Venid = ?"; // Consulta SQL parametrizada
        
        // El bloque try-with-resources gestiona la apertura y cierre automático de la conexión
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) { // Prepara la consulta para evitar SQL Injection
            
            ps.setInt(1, id); // Sustituye el primer signo '?' por el ID recibido
            
            try (ResultSet rs = ps.executeQuery()) { // Ejecuta la consulta y obtiene el set de resultados
                if (rs.next()) { // Verifica si existe al menos un registro en el resultado
                    nombre = rs.getString("Vennombre"); // Extrae el valor de la columna Vennombre
                }
            }
        } catch (SQLException e) { // Captura posibles errores de comunicación con la DB
            System.err.println("Error de consulta: " + e.getMessage());
        }
        return nombre; // Retorna el nombre o una cadena vacía si no hubo éxito
    }

    // Método para filtrar comisiones por un vendedor específico en un rango de fechas
    public List<clsReportes> listarPorFiltro(int idVen, String fInicial, String fFinal) {
        List<clsReportes> lista = new ArrayList<>(); // Crea la lista donde se guardarán los objetos encontrados
        // Consulta SQL con INNER JOIN para unir tablas y BETWEEN para el rango de fechas
        String sql = "SELECT c.Venid, v.Vennombre, c.Comcomision, c.Comfecha " +
                     "FROM comisionesvendedores c " +
                     "INNER JOIN Vendedores v ON c.Venid = v.Venid " +
                     "WHERE c.Venid = ? AND c.Comfecha BETWEEN ? AND ?";

        try (Connection conn = Conexion.getConnection(); // Establece el enlace con MySQL
             PreparedStatement ps = conn.prepareStatement(sql)) { // Prepara la sentencia SQL
            
            ps.setInt(1, idVen); // Asigna el ID del vendedor al primer parámetro
            ps.setString(2, fInicial); // Asigna la fecha inicial al segundo parámetro
            ps.setString(3, fFinal); // Asigna la fecha final al tercer parámetro
            
            try (ResultSet rs = ps.executeQuery()) { // Ejecuta la petición al servidor
                while (rs.next()) { // Itera mientras existan filas en el resultado
                    clsReportes rep = new clsReportes(); // Instancia un nuevo objeto de reporte
                    rep.setVenid(rs.getInt("Venid")); // Llena el ID desde la DB
                    rep.setVen_nombre(rs.getString("Vennombre")); // Llena el nombre desde la DB
                    rep.setComcomision(rs.getDouble("Comcomision")); // Llena la comisión desde la DB
                    rep.setComfecha(rs.getString("Comfecha")); // Llena la fecha desde la DB
                    lista.add(rep); // Agrega el objeto completo a la lista de resultados
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en ejecución de filtro: " + e.getMessage());
        }
        return lista; // Retorna la colección de reportes filtrados
    }

    // Método para insertar un nuevo registro de comisión
    public boolean insertar(clsReportes reporte) {
        // SQL para insertar datos; CURDATE() inserta la fecha actual del sistema automáticamente
        String sql = "INSERT INTO comisionesvendedores (Venid, Commontoventas, Comcomision, Comfecha) " +
                     "VALUES (?, ?, ?, CURDATE())";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, reporte.getVenid()); // Pasa el ID del objeto al SQL
            ps.setDouble(2, 0.0); // Envía un valor por defecto para el monto de ventas
            ps.setDouble(3, reporte.getComcomision()); // Pasa el valor de la comisión al SQL
            
            return ps.executeUpdate() > 0; // Ejecuta la inserción y retorna true si se afectó al menos una fila
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de persistencia: " + e.getMessage());
            return false;
        }
    }
}
