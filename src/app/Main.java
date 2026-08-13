package app;

import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;

public class Main {

    public static void main(String[] args) {

        PedidoComida pedidoComida = new PedidoComida(
                1001,
                "Av. Providencia 123",
                true
        );

        PedidoEncomienda pedidoEncomienda = new PedidoEncomienda(
                1002,
                "Av. Grecia 456",
                5.5,
                true
        );

        PedidoExpress pedidoExpress = new PedidoExpress(
                1003,
                "Av. Las Condes 789",
                1.2,
                true
        );

        Pedido[] pedidos = {
                pedidoComida,
                pedidoEncomienda,
                pedidoExpress
        };

        System.out.println("=== SOBRESCRITURA ===");

        pedidoComida.asignarRepartidor();

        System.out.println();

        pedidoEncomienda.asignarRepartidor();

        System.out.println();

        pedidoExpress.asignarRepartidor();

        System.out.println("\n=== SOBRECARGA ===");

        pedidoComida.asignarRepartidor("Carlos");

        System.out.println();

        pedidoEncomienda.asignarRepartidor("Diego");

        System.out.println();

        pedidoExpress.asignarRepartidor("Andrés");

        System.out.println("\n=== POLIMORFISMO ===");

        for (Pedido pedido : pedidos) {
            pedido.asignarRepartidor();
            System.out.println();
        }
    }
}