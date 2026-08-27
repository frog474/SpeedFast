package app;

import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;
import service.ControladorDeEnvios;

public class Main {

    public static void main(String[] args) {

        PedidoComida pedidoComida = new PedidoComida(
                101,
                "Av. Italia 456",
                4
        );

        PedidoEncomienda pedidoEncomienda = new PedidoEncomienda(
                102,
                "Av. Independencia 123",
                6
        );

        PedidoExpress pedidoExpress = new PedidoExpress(
                103,
                "Av. Apoquindo 1500",
                7
        );

        System.out.println("===== SPEEDFAST =====");
        System.out.println();

        // Asignación automática
        System.out.println("[Pedido Comida]");
        pedidoComida.asignarRepartidor();
        pedidoComida.mostrarResumen();
        System.out.println("Tiempo estimado: "
                + pedidoComida.calcularTiempoEntrega()
                + " minutos");
        System.out.println();

        // Asignación manual usando sobrecarga
        System.out.println("[Pedido Encomienda]");
        pedidoEncomienda.asignarRepartidor("Daniela Tapia");
        pedidoEncomienda.mostrarResumen();
        System.out.println("Tiempo estimado: "
                + pedidoEncomienda.calcularTiempoEntrega()
                + " minutos");
        System.out.println();

        // Pedido Express
        System.out.println("[Pedido Express]");
        pedidoExpress.asignarRepartidor();
        pedidoExpress.mostrarResumen();
        System.out.println("Tiempo estimado: "
                + pedidoExpress.calcularTiempoEntrega()
                + " minutos");
        System.out.println();

        // Polimorfismo
        System.out.println("===== POLIMORFISMO =====");

        Pedido[] pedidos = {
                pedidoComida,
                pedidoEncomienda,
                pedidoExpress
        };

        for (Pedido pedido : pedidos) {
            System.out.println("Pedido #" + pedido.getIdPedido());
            System.out.println("Tiempo estimado: "
                    + pedido.calcularTiempoEntrega()
                    + " minutos");
            System.out.println();
        }

        // Controlador de envíos
        ControladorDeEnvios controlador = new ControladorDeEnvios();

        // Despacho
        System.out.println("===== DESPACHO =====");

        controlador.seleccionarPedido(pedidoComida);
        controlador.despachar();

        controlador.seleccionarPedido(pedidoEncomienda);
        controlador.despachar();

        System.out.println();

        // Cancelación
        System.out.println("Cancelando Pedido Express #103...");

        controlador.seleccionarPedido(pedidoExpress);
        controlador.cancelar();

        System.out.println();

        // Historial
        System.out.println("===== HISTORIAL =====");

        controlador.verHistorial();
    }
}