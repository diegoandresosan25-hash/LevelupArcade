package dao;

import model.Usuario;

public interface UsuarioDAO {
    Usuario buscarPorNombre(String nombreUsuario) throws Exception;
    void insertar(Usuario usuario);
}