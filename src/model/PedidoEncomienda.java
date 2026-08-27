package model;

public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public void asignarRepartidor() {
        this.repartidor = "Daniela Tapia";
        System.out.println("Repartidor asignado automáticamente: " + repartidor);
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (20 + (1.5 * distanciaKm));
    }
}