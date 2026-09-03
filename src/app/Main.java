package app;

import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;
import model.Repartidor;
import service.ControladorDeEnvios;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        // ==============================
        // CREACIÓN DE PEDIDOS
        // ==============================

        PedidoComida pedidoComida1 = new PedidoComida(
                101,
                "Av. Italia 456",
                4
        );

        PedidoEncomienda pedidoEncomienda1 = new PedidoEncomienda(
                102,
                "Av. Independencia 123",
                6
        );

        PedidoExpress pedidoExpress1 = new PedidoExpress(
                103,
                "Av. Apoquindo 1500",
                7
        );

        PedidoComida pedidoComida2 = new PedidoComida(
                104,
                "Av. Providencia 800",
                3
        );

        PedidoEncomienda pedidoEncomienda2 = new PedidoEncomienda(
                105,
                "Av. Grecia 2500",
                8
        );

        PedidoExpress pedidoExpress2 = new PedidoExpress(
                106,
                "Av. Las Condes 1200",
                4
        );

        System.out.println("===== SPEEDFAST =====");
        System.out.println();

        // ==============================
        // ASIGNACIÓN DE REPARTIDORES
        // ==============================

        System.out.println("[Pedido Comida]");
        pedidoComida1.asignarRepartidor();
        pedidoComida1.mostrarResumen();
        System.out.println("Tiempo estimado: "
                + pedidoComida1.calcularTiempoEntrega()
                + " minutos");
        System.out.println();

        System.out.println("[Pedido Encomienda]");
        pedidoEncomienda1.asignarRepartidor("Daniela Tapia");
        pedidoEncomienda1.mostrarResumen();
        System.out.println("Tiempo estimado: "
                + pedidoEncomienda1.calcularTiempoEntrega()
                + " minutos");
        System.out.println();

        System.out.println("[Pedido Express]");
        pedidoExpress1.asignarRepartidor();
        pedidoExpress1.mostrarResumen();
        System.out.println("Tiempo estimado: "
                + pedidoExpress1.calcularTiempoEntrega()
                + " minutos");
        System.out.println();

        // ==============================
        // POLIMORFISMO
        // ==============================

        System.out.println("===== POLIMORFISMO =====");

        Pedido[] pedidos = {
                pedidoComida1,
                pedidoEncomienda1,
                pedidoExpress1
        };

        for (Pedido pedido : pedidos) {
            System.out.println("Pedido #" + pedido.getIdPedido());
            System.out.println("Tiempo estimado: "
                    + pedido.calcularTiempoEntrega()
                    + " minutos");
            System.out.println();
        }

        // ==============================
        // CONTROLADOR DE ENVÍOS
        // ==============================

        ControladorDeEnvios controlador = new ControladorDeEnvios();

        System.out.println("===== DESPACHO =====");

        controlador.seleccionarPedido(pedidoComida1);
        controlador.despachar();

        controlador.seleccionarPedido(pedidoEncomienda1);
        controlador.despachar();

        System.out.println();

        System.out.println("Cancelando Pedido Express #103...");

        controlador.seleccionarPedido(pedidoExpress1);
        controlador.cancelar();

        System.out.println();

        System.out.println("===== HISTORIAL =====");

        controlador.verHistorial();

        // ==============================
        // CONCURRENCIA
        // ==============================

        System.out.println();
        System.out.println("===== SIMULACIÓN DE ENTREGAS CONCURRENTES =====");

        // Pedidos de Camila
        ArrayList<Pedido> pedidosCamila = new ArrayList<>();
        pedidosCamila.add(pedidoComida1);
        pedidosCamila.add(pedidoExpress2);

        // Pedidos de Luis
        ArrayList<Pedido> pedidosLuis = new ArrayList<>();
        pedidosLuis.add(pedidoExpress1);
        pedidosLuis.add(pedidoComida2);

        // Pedidos de Pedro
        ArrayList<Pedido> pedidosPedro = new ArrayList<>();
        pedidosPedro.add(pedidoEncomienda1);
        pedidosPedro.add(pedidoEncomienda2);

        // Creación de los repartidores
        Repartidor camila = new Repartidor(
                "Camila",
                pedidosCamila
        );

        Repartidor luis = new Repartidor(
                "Luis",
                pedidosLuis
        );

        Repartidor pedro = new Repartidor(
                "Pedro",
                pedidosPedro
        );

        // ExecutorService con 3 hilos
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Ejecución concurrente
        executor.execute(camila);
        executor.execute(luis);
        executor.execute(pedro);

        // No aceptar nuevas tareas
        executor.shutdown();

        // Esperar a que terminen todos los repartidores
        try {
            while (!executor.isTerminated()) {
                Thread.sleep(500);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("La simulación fue interrumpida.");
        }

        System.out.println();
        System.out.println("===== TODAS LAS ENTREGAS FINALIZADAS =====");
    }
}