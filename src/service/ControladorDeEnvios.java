package service;

import model.Pedido;

import java.util.ArrayList;

public class ControladorDeEnvios {

    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private ZonaDeCarga zonaDeCarga;

    public ControladorDeEnvios() {
        zonaDeCarga = new ZonaDeCarga();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
        zonaDeCarga.agregarPedido(pedido);
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public ZonaDeCarga getZonaDeCarga() {
        return zonaDeCarga;
    }
}