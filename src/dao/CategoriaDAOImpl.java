package dao;

import model.Categoria;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {

    @Override
    public void insertar(Categoria categoria) {
        String sql = "INSERT INTO categoria (nombre, descripcion) VALUES (?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.executeUpdate();
            System.out.println("✅ Categoría insertada correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar categoría: " + e.getMessage());
        }
    }

    @Override
    public Categoria buscarPorId(int id) {
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Categoria(
                    rs.getInt("id_categoria"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al buscar categoría: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Categoria> listarTodos() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Categoria(
                    rs.getInt("id_categoria"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar categorías: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Categoria categoria) {
        String sql = "UPDATE categoria SET nombre=?, descripcion=? WHERE id_categoria=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.setInt(3, categoria.getIdCategoria());
            ps.executeUpdate();
            System.out.println("✅ Categoría actualizada correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar categoría: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM categoria WHERE id_categoria = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("✅ Categoría eliminada correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar categoría: " + e.getMessage());
        }
    }
}