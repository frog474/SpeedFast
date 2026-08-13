package model;

public class PedidoEncomienda extends Pedido {

    private double peso;
    private boolean embalajeValido;

    public PedidoEncomienda(int idPedido, String direccionEntrega,
                            double peso, boolean embalajeValido) {

        super(idPedido, direccionEntrega, "Encomienda");

        this.peso = peso;
        this.embalajeValido = embalajeValido;
    }

    @Override
    public void asignarRepartidor() {

        System.out.println("=== Pedido de Encomienda ===");
        System.out.println("Pedido: " + idPedido);
        System.out.println("Dirección: " + direccionEntrega);
        System.out.println("Peso: " + peso + " kg");

        if (peso <= 20 && embalajeValido) {

            System.out.println("Peso y embalaje validados.");
            System.out.println("Repartidor asignado correctamente.");

        } else {

            System.out.println("No se puede asignar el repartidor.");
            System.out.println("Verifique el peso y el embalaje.");
        }
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {

        System.out.println("=== Asignación de Encomienda ===");
        System.out.println("Repartidor: " + nombreRepartidor);

        if (peso <= 20 && embalajeValido) {
            System.out.println("Peso validado: " + peso + " kg.");
            System.out.println("Embalaje validado correctamente.");
            System.out.println("Pedido asignado a " + nombreRepartidor + ".");
        } else {
            System.out.println("No se puede asignar el pedido.");
            System.out.println("El peso o embalaje no cumple los requisitos.");
        }
    }
}