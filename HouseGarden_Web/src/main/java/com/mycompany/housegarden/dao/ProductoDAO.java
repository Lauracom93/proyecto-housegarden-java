package com.mycompany.housegarden.dao;

import com.mycompany.housegarden.model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de Productos sobre MySQL usando JDBC.
 * @author Laura Combita
 */
public class ProductoDAO {

    // ─── LISTAR TODOS ───────────────────────────────────────────────────────────
    public List<Producto> listarTodos() throws SQLException {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos ORDER BY id_producto";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    // ─── BUSCAR POR ID ───────────────────────────────────────────────────────────
    public Producto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM productos WHERE id_producto = ?";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    // ─── INSERTAR ────────────────────────────────────────────────────────────────
    public boolean insertar(Producto p) throws SQLException {
        String sql = "INSERT INTO productos (nombre, descripcion, precio, stock, id_categoria, id_proveedor) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setBigDecimal(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getIdCategoria());
            ps.setInt(6, p.getIdProveedor());
            return ps.executeUpdate() > 0;
        }
    }

    // ─── ACTUALIZAR ──────────────────────────────────────────────────────────────
    public boolean actualizar(Producto p) throws SQLException {
        String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=?, stock=?, "
                   + "id_categoria=?, id_proveedor=? WHERE id_producto=?";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setBigDecimal(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getIdCategoria());
            ps.setInt(6, p.getIdProveedor());
            ps.setInt(7, p.getIdProducto());
            return ps.executeUpdate() > 0;
        }
    }

    // ─── ELIMINAR ────────────────────────────────────────────────────────────────
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // ─── MAPEAR ResultSet → Producto ─────────────────────────────────────────────
    private Producto mapear(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setIdProducto(rs.getInt("id_producto"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setPrecio(rs.getBigDecimal("precio"));
        p.setStock(rs.getInt("stock"));
        p.setIdCategoria(rs.getInt("id_categoria"));
        p.setIdProveedor(rs.getInt("id_proveedor"));
        return p;
    }
}
