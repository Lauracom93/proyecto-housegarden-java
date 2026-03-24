package com.mycompany.housegarden.dao;

import com.mycompany.housegarden.model.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de Proveedores sobre MySQL usando JDBC.
 * @author Laura Combita
 */
public class ProveedorDAO {

    public List<Proveedor> listarTodos() throws SQLException {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedores ORDER BY id_proveedor";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public Proveedor buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM proveedores WHERE id_proveedor = ?";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    public boolean insertar(Proveedor p) throws SQLException {
        String sql = "INSERT INTO proveedores (nombre, contacto, telefono) VALUES (?,?,?)";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getContacto());
            ps.setString(3, p.getTelefono());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Proveedor p) throws SQLException {
        String sql = "UPDATE proveedores SET nombre=?, contacto=?, telefono=? WHERE id_proveedor=?";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getContacto());
            ps.setString(3, p.getTelefono());
            ps.setInt(4, p.getIdProveedor());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM proveedores WHERE id_proveedor = ?";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Proveedor mapear(ResultSet rs) throws SQLException {
        return new Proveedor(
            rs.getInt("id_proveedor"),
            rs.getString("nombre"),
            rs.getString("contacto"),
            rs.getString("telefono")
        );
    }
}
