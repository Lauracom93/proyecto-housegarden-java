package com.mycompany.lauracombita_aa2_ev01.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/housegarden";
    private static final String USER = "javauser";
    private static final String PASSWORD = "1234";

    public Connection conectar() {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion exitosa a la base de datos");

        } catch (SQLException e) {

            System.out.println("Error de conexion: " + e.getMessage());

        }

        return conn;
    }

}
