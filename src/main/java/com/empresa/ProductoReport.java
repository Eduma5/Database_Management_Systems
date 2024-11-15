package com.empresa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductoReport {

    public void printAllProductos() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Producto")) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idProducto"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Precio: " + rs.getDouble("precio"));
                System.out.println("Stock: " + rs.getInt("stock"));
                System.out.println("Descripción: " + rs.getString("descripcion"));
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}