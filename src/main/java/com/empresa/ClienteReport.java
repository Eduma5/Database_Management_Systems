package com.empresa;

import com.empresa.ClienteDAO;
import com.empresa.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteReport {

    public void printAllClientes() {
        ClienteDAO clienteDAO = new ClienteDAO();
        try {
            List<Cliente> clientes = clienteDAO.getAllClientes();
            for (Cliente cliente : clientes) {
                System.out.println("ID: " + cliente.getIdCliente());
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("Direccion: " + cliente.getDireccion());
                System.out.println("Telefono: " + cliente.getTelefono());
                System.out.println("Email: " + cliente.getEmail());
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}