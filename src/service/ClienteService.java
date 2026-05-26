package service;

import dao.ClienteDAO;
import dao.ClienteDAOImpl;
import model.Cliente;

import java.util.List;

public class ClienteService {

    private ClienteDAO clienteDAO;

    public ClienteService() {
        this.clienteDAO = new ClienteDAOImpl();
    }

    public void agregarCliente(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
            System.out.println("❌ El nombre del cliente no puede estar vacío.");
            return;
        }
        if (cliente.getEmail() == null || !cliente.getEmail().contains("@")) {
            System.out.println("❌ El email del cliente no es válido.");
            return;
        }
        clienteDAO.insertar(cliente);
    }

    public Cliente buscarCliente(int id) {
        return clienteDAO.buscarPorId(id);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listarTodos();
    }

    public void actualizarCliente(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
            System.out.println("❌ El nombre del cliente no puede estar vacío.");
            return;
        }
        clienteDAO.actualizar(cliente);
    }

    public void eliminarCliente(int id) {
        Cliente c = clienteDAO.buscarPorId(id);
        if (c == null) {
            System.out.println("❌ Cliente no encontrado.");
            return;
        }
        clienteDAO.eliminar(id);
    }
}