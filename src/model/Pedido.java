package model;

import java.util.Date;

public class Pedido {
    private int idPedido;
    private Date fechaPedido;
    private String estado;
    private double total;
    private int idCliente;
    private int idUsuario;

    public Pedido() {}

    public Pedido(int idPedido, Date fechaPedido, String estado, double total, int idCliente, int idUsuario) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.total = total;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
    }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }
    public Date getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(Date fechaPedido) { this.fechaPedido = fechaPedido; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    @Override
    public String toString() {
        return "Pedido [ID=" + idPedido + ", estado=" + estado + ", total=" + total + "]";
    }
}