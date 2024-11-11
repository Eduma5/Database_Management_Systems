// Prueba unitaria para ProductoDAO
package com.empresa;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoDAOTest {

    @Test
    public void testAddProducto() throws SQLException {
        ProductoDAO productoDAO = new ProductoDAO();
        Producto producto = new Producto();
        producto.setNombre("Producto A");
        producto.setPrecio(100.0);
        producto.setStock(50);
        producto.setDescripcion("Descripción del Producto A");

        productoDAO.addProducto(producto);
        Producto retrievedProducto = productoDAO.getProductoById(producto.getIdProducto());
        assertEquals("Producto A", retrievedProducto.getNombre());
    }

    // Otras pruebas unitarias para ProductoDAO...
}