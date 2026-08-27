package service;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;
import model.Pedido;

import java.util.ArrayList;

public class ControladorDeEnvios implements Despachable, Cancelable, Rastreable {

    private ArrayList<String> historial = new ArrayList<>();

    private Pedido pedidoActual;

    public void seleccionarPedido(Pedido pedido) {
        this.pedidoActual = pedido;
    }

    @Override
    public void despachar() {
        if (pedidoActual != null) {
            System.out.println("Pedido #" + pedidoActual.getIdPedido()
                    + " despachado correctamente.");

            historial.add(
                    "Pedido #" + pedidoActual.getIdPedido()
                            + " – entregado por "
                            + pedidoActual.getRepartidor()
            );
        }
    }

    @Override
    public void cancelar() {
        if (pedidoActual != null) {
            System.out.println("→ Pedido #"
                    + pedidoActual.getIdPedido()
                    + " cancelado exitosamente.");
        }
    }

    @Override
    public void verHistorial() {
        System.out.println("Historial:");

        for (String entrega : historial) {
            System.out.println("- " + entrega);
        }
    }
}