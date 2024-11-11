package com.empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public void addProducto(Producto producto) throws SQLException {
        String sql = "INSERT INTO Producto (Nombre, Precio, Stock, Descripcion) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getStock());
            stmt.setString(4, producto.getDescripcion());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    producto.setIdProducto(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating producto failed, no ID obtained.");
                }
            }
        }
    }

    public List<Producto> getAllProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM Producto";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("IDProducto"));
                producto.setNombre(rs.getString("Nombre"));
                producto.setPrecio(rs.getDouble("Precio"));
                producto.setStock(rs.getInt("Stock"));
                producto.setDescripcion(rs.getString("Descripcion"));
                productos.add(producto);
            }
        }
        return productos;
    }

    public Producto getProductoById(int id) throws SQLException {
        Producto producto = null;
        String sql = "SELECT * FROM Producto WHERE IDProducto = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto();
                    producto.setIdProducto(rs.getInt("IDProducto"));
                    producto.setNombre(rs.getString("Nombre"));
                    producto.setPrecio(rs.getDouble("Precio"));
                    producto.setStock(rs.getInt("Stock"));
                    producto.setDescripcion(rs.getString("Descripcion"));
                }
            }
        }
        return producto;
    }

    public void updateProducto(Producto producto) throws SQLException {
        String sql = "UPDATE Producto SET Nombre = ?, Precio = ?, Stock = ?, Descripcion = ? WHERE IDProducto = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getStock());
            stmt.setString(4, producto.getDescripcion());
            stmt.setInt(5, producto.getIdProducto());
            stmt.executeUpdate();
        }
    }

    public void deleteProducto(int id) throws SQLException {
        String sql = "DELETE FROM Producto WHERE IDProducto = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}