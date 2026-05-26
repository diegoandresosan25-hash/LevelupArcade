package controller;

import model.Producto;
import service.ProductoService;

import java.util.List;
import java.util.Scanner;

public class ProductoController {

    private ProductoService productoService;
    private Scanner scanner;

    public ProductoController(Scanner scanner) {
        this.productoService = new ProductoService();
        this.scanner = scanner;
    }

    public void menuProductos() {
        int opcion;
        do {
            System.out.println("\n===== GESTIÓN DE PRODUCTOS =====");
            System.out.println("1. Listar productos");
            System.out.println("2. Buscar producto por ID");
            System.out.println("3. Agregar producto");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: listarProductos(); break;
                case 2: buscarProducto(); break;
                case 3: agregarProducto(); break;
                case 4: actualizarProducto(); break;
                case 5: eliminarProducto(); break;
                case 0: System.out.println("Volviendo al menú principal..."); break;
                default: System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void listarProductos() {
        List<Producto> productos = productoService.listarProductos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            productos.forEach(System.out::println);
        }
    }

    private void buscarProducto() {
        System.out.print("Introduce el ID del producto: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Producto p = productoService.buscarProducto(id);
        if (p != null) System.out.println(p);
        else System.out.println("❌ Producto no encontrado.");
    }

    private void agregarProducto() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        System.out.print("ID Categoría: ");
        int idCategoria = scanner.nextInt();
        scanner.nextLine();
        productoService.agregarProducto(new Producto(0, nombre, descripcion, precio, stock, idCategoria));
    }

    private void actualizarProducto() {
        System.out.print("ID del producto a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nueva descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Nuevo precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Nuevo stock: ");
        int stock = scanner.nextInt();
        System.out.print("Nueva ID Categoría: ");
        int idCategoria = scanner.nextInt();
        scanner.nextLine();
        productoService.actualizarProducto(new Producto(id, nombre, descripcion, precio, stock, idCategoria));
    }

    private void eliminarProducto() {
        System.out.print("ID del producto a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        productoService.eliminarProducto(id);
    }
}