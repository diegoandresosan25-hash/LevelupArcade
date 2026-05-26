package view;

import controller.*;
import model.Usuario;
import service.LlmService;
import service.UsuarioService;

import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private UsuarioService usuarioService;
    private Usuario usuarioActual;
    private LlmService llmService;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.usuarioService = new UsuarioService();
        this.llmService = new LlmService();
    }

    public void iniciar() {
        System.out.println("╔════════════════════════════════╗");
        System.out.println("║      LEVELUP ARCADE            ║");
        System.out.println("║  Sistema de Gestión v1.0       ║");
        System.out.println("╚════════════════════════════════╝");

        if (!login()) {
            System.out.println("❌ No se pudo iniciar sesión. Cerrando...");
            return;
        }

        menuPrincipal();
    }

    private boolean login() {
        System.out.println("\n===== INICIO DE SESIÓN =====");
        System.out.print("Usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        usuarioActual = usuarioService.login(nombre, contrasena);
        return usuarioActual != null;
    }

    private void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("Sesión: " + usuarioActual.getNombreUsuario() + " [" + usuarioActual.getRol() + "]");
            System.out.println("1. Gestión de Productos");
            System.out.println("2. Gestión de Clientes");
            System.out.println("3. Gestión de Proveedores");
            System.out.println("4. Gestión de Categorías");
            System.out.println("5. Inteligencia Artificial");
            System.out.println("0. Cerrar sesión");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion != 0 && opcion != 5 && usuarioActual.getRol().equals("EMPLEADO") && opcion != 1) {
                System.out.println("❌ No tienes permisos para acceder a esta sección.");
                continue;
            }

            switch (opcion) {
                case 1: new ProductoController(scanner).menuProductos(); break;
                case 2: new ClienteController(scanner).menuClientes(); break;
                case 3: new ProveedorController(scanner).menuProveedores(); break;
                case 4: new CategoriaController(scanner).menuCategorias(); break;
                case 5: menuIA(); break;
                case 0: System.out.println("Sesión cerrada."); break;
                default: System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void menuIA() {
        int opcion;
        do {
            System.out.println("\n===== INTELIGENCIA ARTIFICIAL =====");
            System.out.println("1. Generar descripción de producto con IA");
            System.out.println("2. Sugerir categoría para producto con IA");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del producto: ");
                    String nombreDesc = scanner.nextLine();
                    System.out.println("⏳ Generando descripción...");
                    System.out.println(llmService.generarDescripcion(nombreDesc));
                    break;
                case 2:
                    System.out.print("Nombre del producto: ");
                    String nombreCat = scanner.nextLine();
                    System.out.println("⏳ Sugiriendo categoría...");
                    System.out.println(llmService.sugerirCategoria(nombreCat));
                    break;
                case 0: System.out.println("Volviendo..."); break;
                default: System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 0);
    }
}