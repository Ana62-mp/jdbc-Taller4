package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krakedev.entidades.Vehiculo;

public class UpdateVehiculo {

    private static final Logger log = LoggerFactory.getLogger(UpdateVehiculo.class);

    public static void actualizar(Vehiculo vehiculo) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = Conexion.obtenerConexion();

            String sql = "UPDATE vehiculos SET marca = ?, modelo = ?, anio = ?, precio = ?, color = ?, disponible = ? "
                    + "WHERE placa = ?";

            ps = con.prepareStatement(sql);

            ps.setString(1, vehiculo.getMarca());
            ps.setString(2, vehiculo.getModelo());
            ps.setInt(3, vehiculo.getAnio());
            ps.setDouble(4, vehiculo.getPrecio());
            ps.setString(5, vehiculo.getColor());
            ps.setBoolean(6, vehiculo.isDisponible());
            ps.setString(7, vehiculo.getPlaca());

            ps.executeUpdate();

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
        Vehiculo vehiculo = new Vehiculo("ABC1234","Toyota","Corolla",2022,21500.75,"Negro",true,1200);

        actualizar(vehiculo);
    }
}