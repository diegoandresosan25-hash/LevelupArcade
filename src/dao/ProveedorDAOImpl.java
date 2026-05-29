package dao;
import model.Proveedor;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAOImpl implements ProveedorDAO {

    @Override
    public void insertar(Proveedor proveedor) {
        String sql = "INSERT INTO proveedor (nombre, telefono, email, direccion) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getEmail());
            ps.setString(4, proveedor.getDireccion());
            ps.executeUpdate();
            System.out.println("✅ Proveedor insertado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al insertar proveedor: " + e.getMessage());
        }
    }

    @Override
    public Proveedor buscarPorId(int id) {
        String sql = "SELECT * FROM proveedor WHERE id_proveedor = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Proveedor(
                    rs.getInt("id_proveedor"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("direccion")
                );
            }
        } catch (Exception e) {
            System.out.println("❌ Error al buscar proveedor: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Proveedor> listarTodos() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedor";
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Proveedor(
                    rs.getInt("id_proveedor"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("direccion")
                ));
            }
        } catch (Exception e) {
            System.out.println("❌ Error al listar proveedores: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Proveedor proveedor) {
        String sql = "UPDATE proveedor SET nombre=?, telefono=?, email=?, direccion=? WHERE id_proveedor=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getEmail());
            ps.setString(4, proveedor.getDireccion());
            ps.setInt(5, proveedor.getIdProveedor());
            ps.executeUpdate();
            System.out.println("✅ Proveedor actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al actualizar proveedor: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM proveedor WHERE id_proveedor = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("✅ Proveedor eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al eliminar proveedor: " + e.getMessage());
        }
    }
}