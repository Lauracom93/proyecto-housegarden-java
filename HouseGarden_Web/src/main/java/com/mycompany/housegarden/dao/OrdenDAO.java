package com.mycompany.housegarden.dao;

import com.mycompany.housegarden.model.Orden;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de Ordenes sobre MySQL usando JDBC.
 * @author Laura Combita
 */
public class OrdenDAO {

    public List<Orden> listarTodos() throws SQLException {
        List<Orden> lista = new ArrayList<>();
        String sql = "SELECT * FROM ordenes ORDER BY id_orden";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public Orden buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM ordenes WHERE id_orden = ?";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    public boolean insertar(Orden o) throws SQLException {
        String sql = "INSERT INTO ordenes (id_cliente, fecha, estado, total) VALUES (?,?,?,?)";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, o.getIdCliente());
            ps.setDate(2, o.getFecha());
            ps.setString(3, o.getEstado());
            ps.setBigDecimal(4, o.getTotal());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Orden o) throws SQLException {
        String sql = "UPDATE ordenes SET id_cliente=?, fecha=?, estado=?, total=? WHERE id_orden=?";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, o.getIdCliente());
            ps.setDate(2, o.getFecha());
            ps.setString(3, o.getEstado());
            ps.setBigDecimal(4, o.getTotal());
            ps.setInt(5, o.getIdOrden());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM ordenes WHERE id_orden = ?";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Orden mapear(ResultSet rs) throws SQLException {
        return new Orden(
            rs.getInt("id_orden"),
            rs.getInt("id_cliente"),
            rs.getDate("fecha"),
            rs.getString("estado"),
            rs.getBigDecimal("total")
        );
    }
}
