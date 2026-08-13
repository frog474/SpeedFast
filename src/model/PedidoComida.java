package model;

public class PedidoComida extends Pedido {

    private boolean mochilaTermica;

    public PedidoComida(int idPedido, String direccionEntrega, boolean mochilaTermica) {
        super(idPedido, direccionEntrega, "Comida");
        this.mochilaTermica = mochilaTermica;
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("=== Pedido de Comida ===");
        System.out.println("Pedido: " + idPedido);
        System.out.println("Dirección: " + direccionEntrega);

        if (mochilaTermica) {
            System.out.println("Repartidor asignado correctamente.");
            System.out.println("El repartidor cuenta con mochila térmica.");
        } else {
            System.out.println("No se puede asignar el repartidor.");
            System.out.println("Se requiere mochila térmica.");
        }
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {

        System.out.println("=== Asignación de Pedido de Comida ===");
        System.out.println("Repartidor: " + nombreRepartidor);

        if (mochilaTermica) {
            System.out.println("Validación correcta: tiene mochila térmica.");
            System.out.println("Pedido asignado a " + nombreRepartidor + ".");
        } else {
            System.out.println("Validación rechazada: se requiere mochila térmica.");
        }
    }
}