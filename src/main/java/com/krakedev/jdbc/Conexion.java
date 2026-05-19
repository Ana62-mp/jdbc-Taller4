package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Conexion {

	private static final Logger log = LoggerFactory.getLogger(Conexion.class);

	private static final String URL = "jdbc:postgresql://localhost:5432/tallerjdbc";
	private static final String USUARIO = "postgres";
	private static final String CLAVE = "c0tton14";

	public static Connection obtenerConexion() {
		Connection conexion = null;

		try {
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			log.info("Conexión realizada correctamente");
			System.out.println("Conexión exitosa a PostgreSQL");
		} catch (SQLException e) {
			log.error("Error de conexión a PostgreSQL", e);
			System.out.println("Error al conectar con PostgreSQL");
		}

		return conexion;
	}

	public static void main(String[] args) {
		obtenerConexion();
	}
}