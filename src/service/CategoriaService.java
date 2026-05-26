package service;

import dao.CategoriaDAO;
import dao.CategoriaDAOImpl;
import model.Categoria;

import java.util.List;

public class CategoriaService {

    private CategoriaDAO categoriaDAO;

    public CategoriaService() {
        this.categoriaDAO = new CategoriaDAOImpl();
    }

    public void agregarCategoria(Categoria categoria) {
        if (categoria.getNombre() == null || categoria.getNombre().isEmpty()) {
            System.out.println("❌ El nombre de la categoría no puede estar vacío.");
            return;
        }
        categoriaDAO.insertar(categoria);
    }

    public Categoria buscarCategoria(int id) {
        return categoriaDAO.buscarPorId(id);
    }

    public List<Categoria> listarCategorias() {
        return categoriaDAO.listarTodos();
    }

    public void actualizarCategoria(Categoria categoria) {
        if (categoria.getNombre() == null || categoria.getNombre().isEmpty()) {
            System.out.println("❌ El nombre de la categoría no puede estar vacío.");
            return;
        }
        categoriaDAO.actualizar(categoria);
    }

    public void eliminarCategoria(int id) {
        Categoria c = categoriaDAO.buscarPorId(id);
        if (c == null) {
            System.out.println("❌ Categoría no encontrada.");
            return;
        }
        categoriaDAO.eliminar(id);
    }
}