package controller;

import model.Categoria;
import service.CategoriaService;

import java.util.List;
import java.util.Scanner;

public class CategoriaController {

    private CategoriaService categoriaService;
    private Scanner scanner;

    public CategoriaController(Scanner scanner) {
        this.categoriaService = new CategoriaService();
        this.scanner = scanner;
    }

    public void menuCategorias() {
        int opcion;
        do {
            System.out.println("\n===== GESTIÓN DE CATEGORÍAS =====");
            System.out.println("1. Listar categorías");
            System.out.println("2. Buscar categoría por ID");
            System.out.println("3. Agregar categoría");
            System.out.println("4. Actualizar categoría");
            System.out.println("5. Eliminar categoría");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: listarCategorias(); break;
                case 2: buscarCategoria(); break;
                case 3: agregarCategoria(); break;
                case 4: actualizarCategoria(); break;
                case 5: eliminarCategoria(); break;
                case 0: System.out.println("Volviendo al menú principal..."); break;
                default: System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void listarCategorias() {
        List<Categoria> categorias = categoriaService.listarCategorias();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías registradas.");
        } else {
            categorias.forEach(System.out::println);
        }
    }

    private void buscarCategoria() {
        System.out.print("Introduce el ID de la categoría: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Categoria c = categoriaService.buscarCategoria(id);
        if (c != null) System.out.println(c);
        else System.out.println("❌ Categoría no encontrada.");
    }

    private void agregarCategoria() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        categoriaService.agregarCategoria(new Categoria(0, nombre, descripcion));
    }

    private void actualizarCategoria() {
        System.out.print("ID de la categoría a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nueva descripción: ");
        String descripcion = scanner.nextLine();
        categoriaService.actualizarCategoria(new Categoria(id, nombre, descripcion));
    }

    private void eliminarCategoria() {
        System.out.print("ID de la categoría a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        categoriaService.eliminarCategoria(id);
    }
}