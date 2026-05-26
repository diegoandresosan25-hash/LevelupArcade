package service;

import dao.ProveedorDAO;
import dao.ProveedorDAOImpl;
import model.Proveedor;

import java.util.List;

public class ProveedorService {

    private ProveedorDAO proveedorDAO;

    public ProveedorService() {
        this.proveedorDAO = new ProveedorDAOImpl();
    }

    public void agregarProveedor(Proveedor proveedor) {
        if (proveedor.getNombre() == null || proveedor.getNombre().isEmpty()) {
            System.out.println("❌ El nombre del proveedor no puede estar vacío.");
            return;
        }
        if (proveedor.getEmail() == null || !proveedor.getEmail().contains("@")) {
            System.out.println("❌ El email del proveedor no es válido.");
            return;
        }
        proveedorDAO.insertar(proveedor);
    }

    public Proveedor buscarProveedor(int id) {
        return proveedorDAO.buscarPorId(id);
    }

    public List<Proveedor> listarProveedores() {
        return proveedorDAO.listarTodos();
    }

    public void actualizarProveedor(Proveedor proveedor) {
        if (proveedor.getNombre() == null || proveedor.getNombre().isEmpty()) {
            System.out.println("❌ El nombre del proveedor no puede estar vacío.");
            return;
        }
        proveedorDAO.actualizar(proveedor);
    }

    public void eliminarProveedor(int id) {
        Proveedor p = proveedorDAO.buscarPorId(id);
        if (p == null) {
            System.out.println("❌ Proveedor no encontrado.");
            return;
        }
        proveedorDAO.eliminar(id);
    }
}