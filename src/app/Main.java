package app;

import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;

public class Main {

    public static void main(String[] args) {

        PedidoComida pedidoComida = new PedidoComida(
                1,
                "Av. Italia 456",
                4
        );

        PedidoEncomienda pedidoEncomienda = new PedidoEncomienda(
                2,
                "Av. Independencia 123",
                6
        );

        PedidoExpress pedidoExpress = new PedidoExpress(
                3,
                "Av. Apoquindo 1500",
                7
        );

        Pedido[] pedidos = {
                pedidoComida,
                pedidoEncomienda,
                pedidoExpress
        };

        System.out.println("=== TIEMPOS DE ENTREGA ===");
        System.out.println();

        for (Pedido pedido : pedidos) {

            pedido.mostrarResumen();

            System.out.println(
                    "Tiempo estimado de entrega: "
                            + pedido.calcularTiempoEntrega()
                            + " minutos"
            );

            System.out.println();
        }
    }
}