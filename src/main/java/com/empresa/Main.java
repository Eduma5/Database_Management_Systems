package com.empresa;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        ClienteReport clienteReport = new ClienteReport();
        ProductoDAO productoDAO = new ProductoDAO();
        ProductoReport productoReport = new ProductoReport();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Añadir cliente");
            System.out.println("2. Añadir producto");
            System.out.println("3. Consultar clientes");
            System.out.println("4. Consultar productos");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Eliminar producto");
            System.out.println("7. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    // Añadir cliente
                    Cliente nuevoCliente = new Cliente();
                    System.out.print("Ingrese el nombre del cliente: ");
                    nuevoCliente.setNombre(scanner.nextLine());
                    System.out.print("Ingrese la dirección del cliente: ");
                    nuevoCliente.setDireccion(scanner.nextLine());
                    System.out.print("Ingrese el teléfono del cliente: ");
                    nuevoCliente.setTelefono(scanner.nextLine());
                    System.out.print("Ingrese el email del cliente: ");
                    nuevoCliente.setEmail(scanner.nextLine());

                    try {
                        clienteDAO.addCliente(nuevoCliente);
                        System.out.println("Cliente agregado exitosamente.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    // Añadir producto
                    Producto nuevoProducto = new Producto();
                    System.out.print("Ingrese el nombre del producto: ");
                    nuevoProducto.setNombre(scanner.nextLine());
                    System.out.print("Ingrese el precio del producto: ");
                    nuevoProducto.setPrecio(scanner.nextDouble());
                    System.out.print("Ingrese el stock del producto: ");
                    nuevoProducto.setStock(scanner.nextInt());
                    scanner.nextLine(); // Consumir la nueva línea
                    System.out.print("Ingrese la descripción del producto: ");
                    nuevoProducto.setDescripcion(scanner.nextLine());

                    try {
                        productoDAO.addProducto(nuevoProducto);
                        System.out.println("Producto agregado exitosamente.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    // Consultar clientes
                    clienteReport.printAllClientes();
                    break;

                case 4:
                    // Consultar productos
                    productoReport.printAllProductos();
                    break;

                case 5:
                    // Eliminar cliente
                    System.out.print("Ingrese el ID del cliente a eliminar: ");
                    int clienteId = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea
                    try {
                        clienteDAO.deleteCliente(clienteId);
                        System.out.println("Cliente eliminado exitosamente.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 6:
                    // Eliminar producto
                    System.out.print("Ingrese el ID del producto a eliminar: ");
                    int productoId = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea
                    try {
                        productoDAO.deleteProducto(productoId);
                        System.out.println("Producto eliminado exitosamente.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 7:
                    // Salir
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}