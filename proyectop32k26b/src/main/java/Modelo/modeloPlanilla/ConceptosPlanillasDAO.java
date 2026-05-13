/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.modeloPlanilla;
import Controlador.controladorPlanilla.clsConceptosPlanillas;
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
 * @author meilyn garcia 
 */
public class ConceptosPlanillasDAO {
    Conexion cn = new Conexion();

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // INSERTAR
    public boolean insertar(clsConceptosPlanillas cp) {

        String sql = "INSERT INTO conceptosplanilla "
                + "(Connombre, Contipo, Conporcentaje, "
                + "Conmonto, Conaplica, Conestado) "
                + "VALUES (?,?,?,?,?,?)";

        try {

            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, cp.getNombre());
            ps.setString(2, cp.getTipo());
            ps.setDouble(3, cp.getPorcentaje());
            ps.setDouble(4, cp.getMonto());
            ps.setString(5, cp.getAplica());
            ps.setInt(6, cp.getEstado());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println("Error INSERT conceptos: " + e.getMessage());
            return false;
        }
    }

    // LISTAR
    public List<clsConceptosPlanillas> listar() {

        List<clsConceptosPlanillas> lista = new ArrayList<>();

        String sql = "SELECT * FROM conceptosplanilla";

        try {

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                clsConceptosPlanillas cp = new clsConceptosPlanillas();

                cp.setCodigo(rs.getInt("Concodigo"));
                cp.setNombre(rs.getString("Connombre"));
                cp.setTipo(rs.getString("Contipo"));
                cp.setPorcentaje(rs.getDouble("Conporcentaje"));
                cp.setMonto(rs.getDouble("Conmonto"));
                cp.setAplica(rs.getString("Conaplica"));
                cp.setEstado(rs.getInt("Conestado"));

                lista.add(cp);
            }

        } catch (Exception e) {

            System.out.println(e);
        }

        return lista;
    }

    // BUSCAR
    public clsConceptosPlanillas buscar(int codigo) {

        clsConceptosPlanillas cp = new clsConceptosPlanillas();

        String sql = "SELECT * FROM conceptosplanilla "
                + "WHERE Concodigo=?";

        try {

            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, codigo);

            rs = ps.executeQuery();

            while (rs.next()) {

                cp.setCodigo(rs.getInt("Concodigo"));
                cp.setNombre(rs.getString("Connombre"));
                cp.setTipo(rs.getString("Contipo"));
                cp.setPorcentaje(rs.getDouble("Conporcentaje"));
                cp.setMonto(rs.getDouble("Conmonto"));
                cp.setAplica(rs.getString("Conaplica"));
                cp.setEstado(rs.getInt("Conestado"));
            }

        } catch (Exception e) {

            System.out.println(e);
        }

        return cp;
    }

    // MODIFICAR
    public boolean modificar(clsConceptosPlanillas cp) {

        String sql = "UPDATE conceptosplanilla SET "
                + "Connombre=?, "
                + "Contipo=?, "
                + "Conporcentaje=?, "
                + "Conmonto=?, "
                + "Conaplica=?, "
                + "Conestado=? "
                + "WHERE Concodigo=?";

        try {

            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, cp.getNombre());
            ps.setString(2, cp.getTipo());
            ps.setDouble(3, cp.getPorcentaje());
            ps.setDouble(4, cp.getMonto());
            ps.setString(5, cp.getAplica());
            ps.setInt(6, cp.getEstado());
            ps.setInt(7, cp.getCodigo());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            System.out.println(e);

            return false;
        }
    }

    // ELIMINAR LOGICO
    public boolean eliminar(int codigo) {

        String sql = "UPDATE conceptosplanilla "
                + "SET Conestado=0 "
                + "WHERE Concodigo=?";

        try {

            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, codigo);

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            System.out.println(e);

            return false;
        }
    }
}
