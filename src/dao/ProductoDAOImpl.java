package dao;

import model.Producto;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    @Override
    public void insertar(Producto producto) {
        String sql = "INSERT INTO producto (nombre, descripcion, precio, stock, id_categoria) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getIdCategoria());
            ps.executeUpdate();
            System.out.println("✅ Producto insertado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al insertar producto: " + e.getMessage());
        }
    }

    @Override
    public Producto buscarPorId(int id) {
        String sql = "SELECT * FROM producto WHERE id_producto = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getInt("id_categoria")
                );
            }
        } catch (Exception e) {
            System.out.println("❌ Error al buscar producto: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Producto> listarTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getInt("id_categoria")
                ));
            }
        } catch (Exception e) {
            System.out.println("❌ Error al listar productos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Producto producto) {
        String sql = "UPDATE producto SET nombre=?, descripcion=?, precio=?, stock=?, id_categoria=? WHERE id_producto=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getIdCategoria());
            ps.setInt(6, producto.getIdProducto());
            ps.executeUpdate();
            System.out.println("✅ Producto actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al actualizar producto: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM producto WHERE id_producto = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("✅ Producto eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al eliminar producto: " + e.getMessage());
        }
    }
}