package com.empresa;

import java.sql.SQLException;
import java.util.List;

public class ProductoReport {

    public void printAllProductos() {
        ProductoDAO productoDAO = new ProductoDAO();
        try {
            List<Producto> productos = productoDAO.getAllProductos();
            for (Producto producto : productos) {
                System.out.println("ID: " + producto.getIdProducto());
                System.out.println("Nombre: " + producto.getNombre());
                System.out.println("Precio: " + producto.getPrecio());
                System.out.println("Stock: " + producto.getStock());
                System.out.println("Descripción: " + producto.getDescripcion());
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}