package com.empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void addCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (Nombre, Direccion, Telefono, Email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getDireccion());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getEmail());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cliente.setIdCliente(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating cliente failed, no ID obtained.");
                }
            }
        }
    }

    public List<Cliente> getAllClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("IDCliente"));
                cliente.setNombre(rs.getString("Nombre"));
                cliente.setDireccion(rs.getString("Direccion"));
                cliente.setTelefono(rs.getString("Telefono"));
                cliente.setEmail(rs.getString("Email"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public Cliente getClienteById(int id) throws SQLException {
        Cliente cliente = null;
        String sql = "SELECT * FROM Cliente WHERE IDCliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("IDCliente"));
                    cliente.setNombre(rs.getString("Nombre"));
                    cliente.setDireccion(rs.getString("Direccion"));
                    cliente.setTelefono(rs.getString("Telefono"));
                    cliente.setEmail(rs.getString("Email"));
                }
            }
        }
        return cliente;
    }

    public Cliente getClienteByEmail(String email) throws SQLException {
        Cliente cliente = null;
        String sql = "SELECT * FROM Cliente WHERE Email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("IDCliente"));
                    cliente.setNombre(rs.getString("Nombre"));
                    cliente.setDireccion(rs.getString("Direccion"));
                    cliente.setTelefono(rs.getString("Telefono"));
                    cliente.setEmail(rs.getString("Email"));
                }
            }
        }
        return cliente;
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE Cliente SET Nombre = ?, Direccion = ?, Telefono = ?, Email = ? WHERE IDCliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getDireccion());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getEmail());
            stmt.setInt(5, cliente.getIdCliente());
            stmt.executeUpdate();
        }
    }

    public void deleteVentasByClienteId(int clienteId) throws SQLException {
        String sql = "DELETE FROM Venta WHERE IDCliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            stmt.executeUpdate();
        }
    }

    public void deleteCliente(int id) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE IDCliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método en ClienteDAO para eliminar detalleventas por ID de cliente
    public void deleteDetalleVentasByClienteId(int clienteId) throws SQLException {
        String sql = "DELETE FROM detalleventa WHERE IDVenta IN (SELECT IDVenta FROM venta WHERE IDCliente = ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            stmt.executeUpdate();
        }
    }
}