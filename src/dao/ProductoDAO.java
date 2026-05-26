package dao;

import model.Producto;
import java.util.List;

public interface ProductoDAO {
    void insertar(Producto producto);
    Producto buscarPorId(int id);
    List<Producto> listarTodos();
    void actualizar(Producto producto);
    void eliminar(int id);
}