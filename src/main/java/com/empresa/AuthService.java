package com.empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {

    public boolean authenticate(String email, String password) throws SQLException {
        String encryptedPassword = SecurityUtils.encryptPassword(password);
        String sql = "SELECT * FROM Usuario WHERE Email = ? AND Password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, encryptedPassword);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean authorize(int userId, String role) throws SQLException {
        String sql = "SELECT * FROM UserRole WHERE UserId = ? AND Role = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, role);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}