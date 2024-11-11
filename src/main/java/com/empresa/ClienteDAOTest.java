package com.empresa;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteDAOTest {

    private ClienteDAO clienteDAO = new ClienteDAO();



    @Test
    public void testAddCliente() throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan Perez");
        cliente.setDireccion("Calle Falsa 123");
        cliente.setTelefono("123456789");
        cliente.setEmail("juan.perez@example.com");

        clienteDAO.addCliente(cliente);
        Cliente retrievedCliente = clienteDAO.getClienteById(cliente.getIdCliente());
        assertEquals("Juan Perez", retrievedCliente.getNombre());
        assertEquals("Calle Falsa 123", retrievedCliente.getDireccion());
        assertEquals("123456789", retrievedCliente.getTelefono());
        assertEquals("juan.perez@example.com", retrievedCliente.getEmail());
    }

    @Test
    public void testGetClienteById() throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNombre("Maria Lopez");
        cliente.setDireccion("Avenida Siempre Viva 742");
        cliente.setTelefono("987654321");
        cliente.setEmail("maria.lopez@example.com");

        clienteDAO.addCliente(cliente);
        Cliente retrievedCliente = clienteDAO.getClienteById(cliente.getIdCliente());
        assertNotNull(retrievedCliente);
        assertEquals("Maria Lopez", retrievedCliente.getNombre());
    }
    @Test
    public void testUpdateCliente() throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNombre("Carlos Sanchez");
        cliente.setDireccion("Calle Luna 456");
        cliente.setTelefono("555555555");
        cliente.setEmail("carlos.sanchez@example.com");

        clienteDAO.addCliente(cliente);
        cliente.setDireccion("Calle Sol 789");
        clienteDAO.updateCliente(cliente);

        Cliente updatedCliente = clienteDAO.getClienteById(cliente.getIdCliente());
        assertEquals("Calle Sol 789", updatedCliente.getDireccion());
    }
    @Test
    public void testDeleteCliente() throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNombre("Ana Torres");
        cliente.setDireccion("Calle Estrella 101");
        cliente.setTelefono("444444444");
        cliente.setEmail("ana.torres@example.com");

        clienteDAO.addCliente(cliente);
        clienteDAO.deleteCliente(cliente.getIdCliente());

        Cliente deletedCliente = clienteDAO.getClienteById(cliente.getIdCliente());
        assertNull(deletedCliente);
    }
}