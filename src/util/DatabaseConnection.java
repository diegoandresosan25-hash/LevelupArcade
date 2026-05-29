package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static Connection connection = null;

    private static Properties loadProperties() throws Exception {
        Properties props = new Properties();
        InputStream input = DatabaseConnection.class
                .getClassLoader()
                .getResourceAsStream("config.properties");
        if (input == null) throw new Exception("No se encontró config.properties");
        props.load(input);
        return props;
    }

    public static Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            Properties props = loadProperties();
            connection = DriverManager.getConnection(
                    props.getProperty("db.url"),
                    props.getProperty("db.user"),
                    props.getProperty("db.password")
            );
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}