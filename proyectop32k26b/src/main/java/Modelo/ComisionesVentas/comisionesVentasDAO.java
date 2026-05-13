/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.ComisionesVentas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Controlador.ComisionesVentas.clsComisionVentas;
import Modelo.Conexion;
/**
 *
 * @author giron
 */
public class comisionesVentasDAO {
    // Declara el método público que devuelve una lista de objetos modelo clsComisionVentas
    public List<clsComisionVentas> obtenerDatosComisiones() {
        // Inicializa una lista vacía de tipo ArrayList para almacenar los registros recuperados
        List<clsComisionVentas> lista = new ArrayList<>();
        
        // Define la cadena SQL que utiliza LEFT JOIN para unir cinco tablas diferentes en una sola consulta
        String sql = "SELECT cv.id_comision, cv.ven_id, cv.id_empleado, cv.monto_ventas, cv.meta, cv.ventas_adicionales, cv.comision, " +
                     "v.Vennombre, p.Prodnombre, m.marnombre, l.linnombre, l.lincomision " +
                     "FROM ComisionesVendedores cv " +
                     "LEFT JOIN Vendedores v ON cv.ven_id = v.Venid " +
                     "LEFT JOIN productos p ON cv.id_empleado = p.Proid " + 
                     "LEFT JOIN marcas m ON p.Proid = m.marcaid " +
                     "LEFT JOIN lineas l ON m.marcaid = l.lineaid";

        // Abre un bloque try con recursos para asegurar el cierre automático de la conexión y los objetos de base de datos
        try (Connection con = Conexion.getConnection();
             // Prepara la sentencia SQL para su ejecución segura contra inyecciones
             PreparedStatement ps = con.prepareStatement(sql);
             // Ejecuta la consulta y almacena el puntero de los resultados en rs
             ResultSet rs = ps.executeQuery()) {

            // Inicia un bucle que recorre cada fila devuelta por la base de datos
            while (rs.next()) {
                // Crea una nueva instancia del objeto modelo para representar la fila actual
                clsComisionVentas obj = new clsComisionVentas();
                
                // Obtiene el valor de la columna id_comision y lo guarda en el atributo correspondiente
                obj.setId_comision(rs.getInt("id_comision"));
                // Obtiene el ID del vendedor y lo guarda en el objeto
                obj.setVenid(rs.getInt("ven_id"));
                // Obtiene el ID del empleado (relacionado con productos) y lo guarda
                obj.setId_empleado(rs.getInt("id_empleado"));
                // Obtiene el monto de ventas como decimal y lo asigna
                obj.setMonto_ventas(rs.getDouble("monto_ventas"));
                // Obtiene la meta de ventas y la guarda en el objeto
                obj.setMeta(rs.getDouble("meta"));
                // Obtiene las ventas excedentes de la meta y las asigna
                obj.setVentas_adicionales(rs.getDouble("ventas_adicionales"));
                // Obtiene el cálculo final de la comisión devuelto por la base de datos
                obj.setComision(rs.getDouble("comision"));
                // Extrae el nombre del vendedor obtenido a través del JOIN con la tabla Vendedores
                obj.setVennombre(rs.getString("Vennombre"));   
                // Extrae el nombre del producto obtenido a través del JOIN con la tabla productos
                obj.setProdnombre(rs.getString("Prodnombre"));
                // Extrae el nombre de la marca obtenido mediante la relación con la tabla marcas
                obj.setMarnombre(rs.getString("marnombre"));
                // Extrae el nombre de la línea mediante la relación con la tabla lineas
                obj.setLinnombre(rs.getString("linnombre"));
                // Extrae el valor de comisión configurado para esa línea específica
                obj.setLincomision(rs.getDouble("lincomision"));
                
                // Añade el objeto completamente cargado con datos a la lista de retorno
                lista.add(obj);
            }
        // Bloque para manejar excepciones de SQL como errores de sintaxis o pérdida de conexión
        } catch (SQLException e) {
            // Muestra en la consola de salida el mensaje técnico del error ocurrido
            System.out.println("Error en el DAO al obtener datos completos: " + e.getMessage());
        }
        
        // Retorna la lista con todos los objetos recolectados hacia la capa superior
        return lista;
    }
}

