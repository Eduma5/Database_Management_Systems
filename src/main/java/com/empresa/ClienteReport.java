package com.empresa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteReport {

    public void printAllClientes() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Cliente")) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idCliente"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Dirección: " + rs.getString("direccion"));
                System.out.println("Teléfono: " + rs.getString("telefono"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}