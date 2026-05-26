package controller;

import model.Cliente;
import service.ClienteService;

import java.util.List;
import java.util.Scanner;

public class ClienteController {

    private ClienteService clienteService;
    private Scanner scanner;

    public ClienteController(Scanner scanner) {
        this.clienteService = new ClienteService();
        this.scanner = scanner;
    }

    public void menuClientes() {
        int opcion;
        do {
            System.out.println("\n===== GESTIÓN DE CLIENTES =====");
            System.out.println("1. Listar clientes");
            System.out.println("2. Buscar cliente por ID");
            System.out.println("3. Agregar cliente");
            System.out.println("4. Actualizar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: listarClientes(); break;
                case 2: buscarCliente(); break;
                case 3: agregarCliente(); break;
                case 4: actualizarCliente(); break;
                case 5: eliminarCliente(); break;
                case 0: System.out.println("Volviendo al menú principal..."); break;
                default: System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            clientes.forEach(System.out::println);
        }
    }

    private void buscarCliente() {
        System.out.print("Introduce el ID del cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Cliente c = clienteService.buscarCliente(id);
        if (c != null) System.out.println(c);
        else System.out.println("❌ Cliente no encontrado.");
    }

    private void agregarCliente() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        clienteService.agregarCliente(new Cliente(0, nombre, telefono, email, direccion));
    }

    private void actualizarCliente() {
        System.out.print("ID del cliente a actualizar: ");
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
        clienteService.actualizarCliente(new Cliente(id, nombre, telefono, email, direccion));
    }

    private void eliminarCliente() {
        System.out.print("ID del cliente a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        clienteService.eliminarCliente(id);
    }
}