package com.mycompany.lauracombita_aa2_ev01.dao;

import com.mycompany.lauracombita_aa2_ev01.conexion.ConexionBD;
import com.mycompany.lauracombita_aa2_ev01.modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class ProductoDAO {

    ConexionBD conexion = new ConexionBD();

    public void guardarProducto(Producto producto) {

        String sql = "INSERT INTO productos(nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";

        try {

            Connection conn = conexion.conectar();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());

            ps.executeUpdate();

            System.out.println("Producto guardado correctamente");

        } catch (Exception e) {

            System.out.println("Error al guardar producto: " + e.getMessage());

        }

    }

    public void listarProductos() {

        String sql = "SELECT * FROM productos";

        try {

            Connection conn = conexion.conectar();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\nLISTA DE PRODUCTOS");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id_producto") + " | "
                        + rs.getString("nombre") + " | "
                        + rs.getString("descripcion") + " | "
                        + rs.getDouble("precio") + " | "
                        + rs.getInt("stock")
                );

            }

        } catch (Exception e) {

            System.out.println("Error al listar productos: " + e.getMessage());

        }

    }

}
