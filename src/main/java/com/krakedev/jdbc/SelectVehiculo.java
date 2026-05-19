package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectVehiculo {

	private static final Logger log = LoggerFactory.getLogger(SelectVehiculo.class);

	public static void listar() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = Conexion.obtenerConexion();

			String sql = "SELECT placa, marca, modelo, anio, precio, color, disponible, kilometraje FROM vehiculos";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				log.info("Placa: " + rs.getString("placa"));
				log.info("Marca: " + rs.getString("marca"));
				log.info("Modelo: " + rs.getString("modelo"));
				log.info("Año: " + rs.getInt("anio"));
				log.info("Precio: " + rs.getDouble("precio"));
				log.info("Color: " + rs.getString("color"));
				log.info("Disponible: " + rs.getBoolean("disponible"));
				log.info("Kilometraje: " + rs.getInt("kilometraje"));
				log.info("-----------------------------");
			}

			log.info("Vehículos listados correctamente");

		} catch (SQLException e) {
			log.error("Error al listar vehículos", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
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
		listar();
	}
}