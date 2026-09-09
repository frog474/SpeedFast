package view;

import model.Pedido;
import service.ControladorDeEnvios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaListaPedidos extends JFrame {

    private JPanel panel1;
    private JTable tablaPedidos;
    private JButton btnActualizar;

    private ControladorDeEnvios controlador;
    private DefaultTableModel modeloTabla;

    public VentanaListaPedidos(ControladorDeEnvios controlador) {

        this.controlador = controlador;

        setTitle("Lista de Pedidos");
        setContentPane(panel1);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Estado");

        tablaPedidos.setModel(modeloTabla);

        actualizarTabla();

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTabla();
            }
        });
    }

    private void actualizarTabla() {

        modeloTabla.setRowCount(0);

        for (Pedido pedido : controlador.getPedidos()) {

            modeloTabla.addRow(new Object[]{
                    pedido.getId(),
                    pedido.getDireccionEntrega(),
                    pedido.getEstado()
            });
        }
    }
}