package model;

import service.ZonaDeCarga;

import java.util.Random;

public class Repartidor implements Runnable {

    private String nombre;
    private ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    @Override
    public void run() {

        Random random = new Random();

        while (true) {

            Pedido pedido = zonaDeCarga.retirarPedido();

            if (pedido == null) {
                System.out.println("[Zona de carga vacía]");
                break;
            }

            pedido.setEstado(EstadoPedido.EN_REPARTO);

            System.out.println("[Repartidor - " + nombre
                    + "] Retirando pedido #" + pedido.getId() + "...");

            System.out.println("[Repartidor - " + nombre
                    + "] Estado: " + pedido.getEstado());

            System.out.println("[Repartidor - " + nombre
                    + "] Entregando pedido #" + pedido.getId() + "...");

            try {
                int tiempoEspera = 1000 + random.nextInt(3000);
                Thread.sleep(tiempoEspera);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

                System.out.println("[Repartidor - " + nombre
                        + "] Entrega interrumpida.");

                return;
            }

            pedido.setEstado(EstadoPedido.ENTREGADO);

            System.out.println("[Repartidor - " + nombre
                    + "] Estado: " + pedido.getEstado());
        }
    }
}