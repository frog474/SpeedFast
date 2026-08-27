package model;

public abstract class Pedido {

    protected int idPedido;
    protected String direccionEntrega;
    protected double distanciaKm;
    protected String repartidor;

    public Pedido(int idPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
    }

    // Sobrecarga: asignación manual por nombre
    public void asignarRepartidor(String nombreRepartidor) {
        this.repartidor = nombreRepartidor;
        System.out.println("Repartidor asignado: " + nombreRepartidor);
    }

    // Método que las clases hijas sobrescribirán
    public void asignarRepartidor() {
        System.out.println("Asignando repartidor para el pedido " + idPedido);
    }

    // Método implementado en la clase abstracta
    public void mostrarResumen() {
        System.out.println("Pedido #" + idPedido);
        System.out.println("Dirección: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
    }

    // Cada tipo de pedido calculará su propio tiempo
    public abstract int calcularTiempoEntrega();

    public int getIdPedido() {
        return idPedido;
    }

    public String getRepartidor() {
        return repartidor;
    }
}