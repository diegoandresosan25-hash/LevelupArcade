package dao;

import model.Proveedor;
import java.util.List;

public interface ProveedorDAO {
    void insertar(Proveedor proveedor);
    Proveedor buscarPorId(int id);
    List<Proveedor> listarTodos();
    void actualizar(Proveedor proveedor);
    void eliminar(int id);
}