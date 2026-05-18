/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.modeloPlanilla;

import Controlador.controladorPlanilla.clsBitacoraPlanilla;
import Modelo.Conexion;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 *
 * @author Meilyn Garcia 9959-23-17838
 */
public class BitacoraPlanillaDAO {


        public void insertarBitacora(
                int idUsuario,
                int codigoAplicacion,
                String accion) {

            try {

                Connection conexion
                        = Conexion.getConnection();

                String sql
                        = "INSERT INTO bitacora "
                        + "(UsuId, Aplcodigo, Bitfecha, "
                        + "Bitip, Bitequipo, Bitaccion) "
                        + "VALUES (?, ?, NOW(), ?, ?, ?)";

                PreparedStatement ps
                        = conexion.prepareStatement(sql);

                String ip
                        = InetAddress.getLocalHost()
                                .getHostAddress();

                String equipo
                        = InetAddress.getLocalHost()
                                .getHostName();

                ps.setInt(1, idUsuario);
                ps.setInt(2, codigoAplicacion);
                ps.setString(3, ip);
                ps.setString(4, equipo);
                ps.setString(5, accion);

                ps.executeUpdate();

                ps.close();
                conexion.close();

            } catch (Exception e) {

                System.out.println(
                        "Error bitacora: "
                        + e.getMessage());
            }
        }
    
}
