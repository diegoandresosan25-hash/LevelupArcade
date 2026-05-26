package dao;

import model.Categoria;
import java.util.List;

public interface CategoriaDAO {
    void insertar(Categoria categoria);
    Categoria buscarPorId(int id);
    List<Categoria> listarTodos();
    void actualizar(Categoria categoria);
    void eliminar(int id);
}