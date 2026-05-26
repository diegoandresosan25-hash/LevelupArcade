package service;

import dao.ProductoDAO;
import dao.ProductoDAOImpl;
import model.Producto;

import java.util.List;

public class ProductoService {

    private ProductoDAO productoDAO;

    public ProductoService() {
        this.productoDAO = new ProductoDAOImpl();
    }

    public void agregarProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            System.out.println("❌ El nombre del producto no puede estar vacío.");
            return;
        }
        if (producto.getPrecio() <= 0) {
            System.out.println("❌ El precio debe ser mayor que 0.");
            return;
        }
        if (producto.getStock() < 0) {
            System.out.println("❌ El stock no puede ser negativo.");
            return;
        }
        productoDAO.insertar(producto);
    }

    public Producto buscarProducto(int id) {
        return productoDAO.buscarPorId(id);
    }

    public List<Producto> listarProductos() {
        return productoDAO.listarTodos();
    }

    public void actualizarProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            System.out.println("❌ El nombre del producto no puede estar vacío.");
            return;
        }
        if (producto.getPrecio() <= 0) {
            System.out.println("❌ El precio debe ser mayor que 0.");
            return;
        }
        productoDAO.actualizar(producto);
    }

    public void eliminarProducto(int id) {
        Producto p = productoDAO.buscarPorId(id);
        if (p == null) {
            System.out.println("❌ Producto no encontrado.");
            return;
        }
        productoDAO.eliminar(id);
    }
}