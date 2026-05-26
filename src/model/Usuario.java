package model;

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String contrasenaHash;
    private String rol;

    public Usuario() {}

    public Usuario(int idUsuario, String nombreUsuario, String contrasenaHash, String rol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenaHash = contrasenaHash;
        this.rol = rol;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public String getContrasenaHash() { return contrasenaHash; }
    public void setContrasenaHash(String contrasenaHash) { this.contrasenaHash = contrasenaHash; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    @Override
    public String toString() {
        return "Usuario [ID=" + idUsuario + ", nombre=" + nombreUsuario + ", rol=" + rol + "]";
    }
}