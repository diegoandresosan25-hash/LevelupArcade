package dao;

import model.Usuario;
import util.DatabaseConnection;

import java.sql.*;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public Usuario buscarPorNombre(String nombreUsuario) {
        String sql = "SELECT * FROM usuario WHERE nombre_usuario = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre_usuario"),
                    rs.getString("contrasena_hash"),
                    rs.getString("rol")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al buscar usuario: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void insertar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nombre_usuario, contrasena_hash, rol) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasenaHash());
            ps.setString(3, usuario.getRol());
            ps.executeUpdate();
            System.out.println("✅ Usuario insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar usuario: " + e.getMessage());
        }
    }
}