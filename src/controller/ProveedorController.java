package controller;

import model.Proveedor;
import service.ProveedorService;

import java.util.List;
import java.util.Scanner;

public class ProveedorController {

    private ProveedorService proveedorService;
    private Scanner scanner;

    public ProveedorController(Scanner scanner) {
        this.proveedorService = new ProveedorService();
        this.scanner = scanner;
    }

    public void menuProveedores() {
        int opcion;
        do {
            System.out.println("\n===== GESTIÓN DE PROVEEDORES =====");
            System.out.println("1. Listar proveedores");
            System.out.println("2. Buscar proveedor por ID");
            System.out.println("3. Agregar proveedor");
            System.out.println("4. Actualizar proveedor");
            System.out.println("5. Eliminar proveedor");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: listarProveedores(); break;
                case 2: buscarProveedor(); break;
                case 3: agregarProveedor(); break;
                case 4: actualizarProveedor(); break;
                case 5: eliminarProveedor(); break;
                case 0: System.out.println("Volviendo al menú principal..."); break;
                default: System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void listarProveedores() {
        List<Proveedor> proveedores = proveedorService.listarProveedores();
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            proveedores.forEach(System.out::println);
        }
    }

    private void buscarProveedor() {
        System.out.print("Introduce el ID del proveedor: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Proveedor p = proveedorService.buscarProveedor(id);
        if (p != null) System.out.println(p);
        else System.out.println("❌ Proveedor no encontrado.");
    }

    private void agregarProveedor() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        proveedorService.agregarProveedor(new Proveedor(0, nombre, telefono, email, direccion));
    }

    private void actualizarProveedor() {
        System.out.print("ID del proveedor a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nuevo teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Nuevo email: ");
        String email = scanner.nextLine();
        System.out.print("Nueva dirección: ");
        String direccion = scanner.nextLine();
        proveedorService.actualizarProveedor(new Proveedor(id, nombre, telefono, email, direccion));
    }

    private void eliminarProveedor() {
        System.out.print("ID del proveedor a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        proveedorService.eliminarProveedor(id);
    }
}