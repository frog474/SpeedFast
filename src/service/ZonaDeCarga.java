package service;

import model.EstadoPedido;
import model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class ZonaDeCarga {

    private List<Pedido> pedidos;

    public ZonaDeCarga() {
        pedidos = new ArrayList<>();
        System.out.println("[Zona de carga inicializada]");
    }

    public synchronized void agregarPedido(Pedido p) {
        pedidos.add(p);

        System.out.println("Pedido #" + p.getId()
                + " agregado. Destino: "
                + p.getDireccionEntrega());
    }

    public synchronized Pedido retirarPedido() {

        for (Pedido pedido : pedidos) {

            if (pedido.getEstado() == EstadoPedido.PENDIENTE) {

                pedidos.remove(pedido);

                return pedido;
            }
        }

        return null;
    }
}