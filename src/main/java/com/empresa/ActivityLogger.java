package com.empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ActivityLogger {

    public static void logActivity(int userId, String activity) throws SQLException {
        String sql = "INSERT INTO ActivityLog (UserId, Activity, Timestamp) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, activity);
            stmt.setObject(3, LocalDateTime.now());
            stmt.executeUpdate();
        }
    }
}