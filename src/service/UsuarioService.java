package service;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import model.Usuario;
import util.PasswordUtil;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    public Usuario login(String nombreUsuario, String contrasena) {
        Usuario usuario = usuarioDAO.buscarPorNombre(nombreUsuario);
        if (usuario == null) {
            System.out.println("❌ Usuario no encontrado.");
            return null;
        }
        if (!PasswordUtil.verificarPassword(contrasena, usuario.getContrasenaHash())) {
            System.out.println("❌ Contraseña incorrecta.");
            return null;
        }
        System.out.println("✅ Login correcto. Bienvenido " + usuario.getNombreUsuario() + " [" + usuario.getRol() + "]");
        return usuario;
    }

    public void registrarUsuario(String nombreUsuario, String contrasena, String rol) {
        String hash = PasswordUtil.hashPassword(contrasena);
        usuarioDAO.insertar(new Usuario(0, nombreUsuario, hash, rol));
    }
}