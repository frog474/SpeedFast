package model;

import java.util.ArrayList;
import java.util.Random;

public class Repartidor implements Runnable {

    private String nombre;
    private ArrayList<Pedido> pedidosAsignados;

    public Repartidor(String nombre, ArrayList<Pedido> pedidosAsignados) {
        this.nombre = nombre;
        this.pedidosAsignados = pedidosAsignados;
    }

    @Override
    public void run() {

        Random random = new Random();

        for (Pedido pedido : pedidosAsignados) {

            System.out.println("[Repartidor: " + nombre + "] Entregando "
                    + pedido.getClass().getSimpleName()
                    + " #" + pedido.getIdPedido() + "...");

            try {
                int tiempoEspera = 1000 + random.nextInt(3000);
                Thread.sleep(tiempoEspera);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[Repartidor: " + nombre
                        + "] Entrega interrumpida.");
                return;
            }

            System.out.println("[Repartidor: " + nombre
                    + "] Pedido #" + pedido.getIdPedido()
                    + " entregado.");
        }
    }
}