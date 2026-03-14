package com.mycompany.housegarden.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexion con la base de datos MySQL.
 * Proyecto: HouseGarden
 * @author Laura Combita
 */
public class ConexionDB {

    // Parametros de conexion
    private static final String URL      = "jdbc:mysql://127.0.0.1:3306/housegarden";
    private static final String USUARIO  = "root";
    private static final String PASSWORD = "";

    /**
     * Obtiene una conexion activa con la base de datos.
     * @return objeto Connection
     * @throws SQLException si ocurre un error de conexion
     */
    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL no encontrado: " + e.getMessage());
        }
    }
}
