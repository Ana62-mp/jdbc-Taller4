package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krakedev.entidades.Vehiculo;

public class InsertVehiculo {

    private static final Logger log = LoggerFactory.getLogger(InsertVehiculo.class);

    public static void insertar(Vehiculo vehiculo) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = Conexion.obtenerConexion();

            String sql = "INSERT INTO vehiculos (placa, marca, modelo, anio, precio, color, disponible) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, vehiculo.getPlaca());
            ps.setString(2, vehiculo.getMarca());
            ps.setString(3, vehiculo.getModelo());
            ps.setInt(4, vehiculo.getAnio());
            ps.setDouble(5, vehiculo.getPrecio());
            ps.setString(6, vehiculo.getColor());
            ps.setBoolean(7, vehiculo.isDisponible());

            ps.executeUpdate();

            System.out.println("Vehículo insertado.");
            log.info("Vehículo insertado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar vehículo");
            log.error("Error al insertar vehículo", e);
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
        Vehiculo vehiculo = new Vehiculo("JUA6676", "Toyota", "Corolla", 2020, 18500.50, "Blanco", true);
        insertar(vehiculo);
    }
}