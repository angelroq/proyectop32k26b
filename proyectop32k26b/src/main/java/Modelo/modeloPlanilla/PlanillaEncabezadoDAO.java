/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.modeloPlanilla;

import Controlador.controladorPlanilla.clsPlanillaEncabezado;
import Controlador.clsBitacora;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author usuario
 */
public class PlanillaEncabezadoDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // INSERTAR
    public boolean insertar(clsPlanillaEncabezado p) {
        String sql = "INSERT INTO planillaencabezado (Plafecha, Platotal, Plaestado, Movbid) VALUES (?, ?, ?, ?)";

        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, p.getPlafecha());
            ps.setDouble(2, p.getPlatotal());
            ps.setInt(3, p.getPlaestado());

            if (p.getMovbid() == 0) {
                ps.setNull(4, java.sql.Types.INTEGER);
            } else {
                ps.setInt(4, p.getMovbid());
            }

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error INSERT planilla: " + e.getMessage());
            return false;
        }
    }

    // LISTAR TODO
    public List<clsPlanillaEncabezado> listar() {

        List<clsPlanillaEncabezado> lista = new ArrayList<>();

        String sql = "SELECT * FROM planillaencabezado";

        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                clsPlanillaEncabezado p = new clsPlanillaEncabezado();

                p.setPlacodigo(rs.getInt("Placodigo"));
                p.setPlafecha(rs.getString("Plafecha"));
                p.setPlatotal(rs.getDouble("Platotal"));
                p.setPlaestado(rs.getInt("Plaestado"));
                p.setMovbid(rs.getInt("Movbid"));

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error LISTAR planilla: " + e.getMessage());
        }

        return lista;
    }

    // BUSCAR POR ID
    public clsPlanillaEncabezado buscar(int id) {

        String sql = "SELECT * FROM planillaencabezado WHERE Placodigo = ?";

        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                clsPlanillaEncabezado p = new clsPlanillaEncabezado();

                p.setPlacodigo(rs.getInt("Placodigo"));
                p.setPlafecha(rs.getString("Plafecha"));
                p.setPlatotal(rs.getDouble("Platotal"));
                p.setPlaestado(rs.getInt("Plaestado"));
                p.setMovbid(rs.getInt("Movbid"));

                return p;
            }

        } catch (SQLException e) {
            System.out.println("Error BUSCAR planilla: " + e.getMessage());
        }

        return null;
    }

    // ACTUALIZAR
    public boolean actualizar(clsPlanillaEncabezado p) {

        String sql = "UPDATE planillaencabezado SET Plafecha=?, Platotal=?, Plaestado=?, Movbid=? WHERE Placodigo=?";

        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, p.getPlafecha());
            ps.setDouble(2, p.getPlatotal());
            ps.setInt(3, p.getPlaestado());

            if (p.getMovbid() == 0) {
                ps.setNull(4, java.sql.Types.INTEGER);
            } else {
                ps.setInt(4, p.getMovbid());
            }

            ps.setInt(5, p.getPlacodigo());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error UPDATE planilla: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminar(int id) {

        String sql = "DELETE FROM planillaencabezado WHERE Placodigo=?";

        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error DELETE planilla: " + e.getMessage());
            return false;
        }
    }
}

