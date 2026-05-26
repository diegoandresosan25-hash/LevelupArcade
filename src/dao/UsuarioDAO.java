package dao;

import model.Usuario;

public interface UsuarioDAO {
    Usuario buscarPorNombre(String nombreUsuario);
    void insertar(Usuario usuario);
}