/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.comisionesVentas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Controlador.comisionesVentas.clsComisionesVentas;
import Modelo.Conexion;
/**
 *
 * @author giron
 */
public class ComisionesVentasDAO {
    public List<clsComisionesVentas> obtenerDatosComisiones() {
        List<clsComisionesVentas> lista = new ArrayList<>();
        String sql = "SELECT id_comision, ven_id, id_empleado, monto_ventas, meta, ventas_adicionales, comision FROM ComisionesVendedores";
        try {

            Connection con = Conexion.getConnection(); 
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clsComisionesVentas obj = new clsComisionesVentas();
                obj.setId_comision(rs.getInt("id_comision"));
                obj.setVen_id(rs.getInt("ven_id"));
                obj.setId_empleado(rs.getInt("id_empleado"));
                obj.setMonto_ventas(rs.getDouble("monto_ventas"));
                obj.setMeta(rs.getDouble("meta"));
                obj.setVentas_adicionales(rs.getDouble("ventas_adicionales"));
                obj.setComision(rs.getDouble("comision"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            System.out.println("Error en el DAO: " + e.getMessage());
        }
        
        return lista;
    }
}
