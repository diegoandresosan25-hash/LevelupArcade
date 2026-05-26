package dao;

import model.Cliente;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public void insertar(Cliente cliente) {
        String sql = "INSERT INTO cliente (nombre, telefono, email, direccion) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getTelefono());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getDireccion());
            ps.executeUpdate();
            System.out.println("✅ Cliente insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar cliente: " + e.getMessage());
        }
    }

    @Override
    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("direccion")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("direccion")
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre=?, telefono=?, email=?, direccion=? WHERE id_cliente=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getTelefono());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getDireccion());
            ps.setInt(5, cliente.getIdCliente());
            ps.executeUpdate();
            System.out.println("✅ Cliente actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar cliente: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("✅ Cliente eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar cliente: " + e.getMessage());
        }
    }
}