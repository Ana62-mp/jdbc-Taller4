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

			String sql = "SELECT placa, marca, modelo, anio, precio, color, disponible FROM vehiculos";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("Placa: " + rs.getString("placa"));
				System.out.println("Marca: " + rs.getString("marca"));
				System.out.println("Modelo: " + rs.getString("modelo"));
				System.out.println("Año: " + rs.getInt("anio"));
				System.out.println("Precio: " + rs.getDouble("precio"));
				System.out.println("Color: " + rs.getString("color"));
				System.out.println("Disponible: " + rs.getBoolean("disponible"));
				System.out.println("-----------------------------");
			}

			log.info("Vehículos listados correctamente");

		} catch (SQLException e) {
			System.out.println("Error al listar vehículos");
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