package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final String LOG_FILE = "logs/actividad.log";
    private static final String ERROR_FILE = "logs/errores.log";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String mensaje) {
        escribir(LOG_FILE, "[INFO] " + mensaje);
    }

    public static void error(String mensaje) {
        escribir(ERROR_FILE, "[ERROR] " + mensaje);
    }

    private static void escribir(String archivo, String mensaje) {
        new java.io.File("logs").mkdirs();
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
            pw.println(LocalDateTime.now().format(formatter) + " - " + mensaje);
        } catch (IOException e) {
            System.out.println("❌ Error al escribir log: " + e.getMessage());
        }
    }
}