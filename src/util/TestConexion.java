package util;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {

    public static void main(String[] args) {
        try {
            Connection con = DatabaseConnection.getConnection();
            if (con != null) {
                System.out.println("✅ Conexión exitosa a la base de datos.");
                DatabaseConnection.closeConnection();
            }
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        }
    }
}