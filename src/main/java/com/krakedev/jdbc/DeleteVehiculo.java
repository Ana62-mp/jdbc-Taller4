package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteVehiculo {

	private static final Logger log = LoggerFactory.getLogger(DeleteVehiculo.class);

	public static void eliminar(String placa) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = Conexion.obtenerConexion();

			String sql = "DELETE FROM vehiculos WHERE placa = ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, placa);

			ps.executeUpdate();

			log.info("Vehículo eliminado correctamente");

		} catch (SQLException e) {
			log.error("Error al eliminar vehículo", e);
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
		eliminar("ABC1234");
	}
}