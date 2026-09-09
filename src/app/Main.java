package app;

import model.EstadoPedido;
import model.Pedido;
import model.Repartidor;
import service.ZonaDeCarga;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== SPEEDFAST =====");
        System.out.println();

        // Crear la zona de carga compartida
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        // Crear pedidos
        zonaDeCarga.agregarPedido(
                new Pedido(1, "Santiago Centro", EstadoPedido.PENDIENTE)
        );

        zonaDeCarga.agregarPedido(
                new Pedido(2, "Providencia", EstadoPedido.PENDIENTE)
        );

        zonaDeCarga.agregarPedido(
                new Pedido(3, "Ñuñoa", EstadoPedido.PENDIENTE)
        );

        zonaDeCarga.agregarPedido(
                new Pedido(4, "Recoleta", EstadoPedido.PENDIENTE)
        );

        zonaDeCarga.agregarPedido(
                new Pedido(5, "Las Condes", EstadoPedido.PENDIENTE)
        );

        System.out.println();

        // Crear los repartidores
        Repartidor juan = new Repartidor("Juan", zonaDeCarga);
        Repartidor camila = new Repartidor("Camila", zonaDeCarga);
        Repartidor pedro = new Repartidor("Pedro", zonaDeCarga);

        // Crear un ExecutorService con 3 hilos
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Ejecutar los repartidores en paralelo
        executor.execute(juan);
        executor.execute(camila);
        executor.execute(pedro);

        // No aceptar nuevas tareas
        executor.shutdown();

        // Esperar a que todos los repartidores terminen
        try {
            while (!executor.isTerminated()) {
                Thread.sleep(500);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("La simulación fue interrumpida.");
        }

        System.out.println();
        System.out.println("Todos los pedidos han sido entregados correctamente");
    }
}