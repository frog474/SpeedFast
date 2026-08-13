package model;

public class PedidoExpress extends Pedido {

    private double distanciaRepartidor;
    private boolean disponibleInmediatamente;

    public PedidoExpress(int idPedido, String direccionEntrega,
                         double distanciaRepartidor,
                         boolean disponibleInmediatamente) {

        super(idPedido, direccionEntrega, "Compra Express");

        this.distanciaRepartidor = distanciaRepartidor;
        this.disponibleInmediatamente = disponibleInmediatamente;
    }

    @Override
    public void asignarRepartidor() {

        System.out.println("=== Pedido Express ===");
        System.out.println("Pedido: " + idPedido);
        System.out.println("Dirección: " + direccionEntrega);
        System.out.println("Distancia del repartidor: "
                + distanciaRepartidor + " km");

        if (disponibleInmediatamente) {

            System.out.println("El repartidor está disponible inmediatamente.");
            System.out.println("Pedido asignado al repartidor más cercano.");

        } else {

            System.out.println("No hay repartidores disponibles inmediatamente.");
        }
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {

        System.out.println("=== Asignación de Compra Express ===");
        System.out.println("Repartidor: " + nombreRepartidor);
        System.out.println("Distancia: " + distanciaRepartidor + " km");

        if (disponibleInmediatamente) {
            System.out.println("Disponibilidad inmediata confirmada.");
            System.out.println("Pedido asignado a " + nombreRepartidor + ".");
        } else {
            System.out.println("El repartidor no está disponible inmediatamente.");
        }
    }
}