package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateVehiculo {

    private static final Logger log = LoggerFactory.getLogger(UpdateVehiculo.class);

    public static void actualizarPrecio(String placa, double nuevoPrecio) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = Conexion.obtenerConexion();

            String sql = "UPDATE vehiculos SET precio = ? WHERE placa = ?";

            ps = con.prepareStatement(sql);
            ps.setDouble(1, nuevoPrecio);
            ps.setString(2, placa);

            ps.executeUpdate();

            System.out.println("Vehículo actualizado.");
            log.info("Vehículo actualizado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al actualizar vehículo");
            log.error("Error al actualizar vehículo", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                log.error("Error al cerrar recursos", e);
            }
        }
    }

    public static void main(String[] args) {
        actualizarPrecio("ABC1234", 199.99);
    }
}