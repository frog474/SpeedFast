package view;

import model.Repartidor;
import service.ControladorDeEnvios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {

    private JPanel panel1;
    private JLabel lblTitulo;
    private JButton btnRegistrar;
    private JButton btnListar;
    private JButton btnEntrega;

    private ControladorDeEnvios controlador;

    public VentanaPrincipal() {

        controlador = new ControladorDeEnvios();

        setTitle("SpeedFast - Gestión de Entregas");
        setContentPane(panel1);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Botón Registrar pedido
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaRegistroPedido(controlador).setVisible(true);
            }
        });

        // Botón Listar pedidos
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaListaPedidos(controlador).setVisible(true);
            }
        });

        // Botón Iniciar entrega
        btnEntrega.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!controlador.getZonaDeCarga().hayPedidosPendientes()) {

                    JOptionPane.showMessageDialog(
                            VentanaPrincipal.this,
                            "No hay pedidos pendientes para entregar.",
                            "Sin pedidos",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }

                Repartidor repartidor = new Repartidor(
                        "Juan",
                        controlador.getZonaDeCarga()
                );

                Thread hilo = new Thread(repartidor);
                hilo.start();

                JOptionPane.showMessageDialog(
                        VentanaPrincipal.this,
                        "La entrega ha comenzado.",
                        "Entrega iniciada",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }
}