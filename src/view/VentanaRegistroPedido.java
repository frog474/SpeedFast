package view;

import model.EstadoPedido;
import model.Pedido;
import service.ControladorDeEnvios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistroPedido extends JFrame {

    private JPanel panel1;
    private JLabel lblId;
    private JTextField txtId;
    private JLabel lblDireccion;
    private JTextField txtDireccion;
    private JLabel lblTipo;
    private JComboBox cmbTipo;
    private JButton btnGuardar;

    private ControladorDeEnvios controlador;

    public VentanaRegistroPedido(ControladorDeEnvios controlador) {

        this.controlador = controlador;

        setTitle("Registrar Pedido");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String idTexto = txtId.getText().trim();
                String direccion = txtDireccion.getText().trim();
                String tipo = (String) cmbTipo.getSelectedItem();

                if (idTexto.isEmpty() || direccion.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            VentanaRegistroPedido.this,
                            "Debe completar todos los campos.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                int id;

                try {
                    id = Integer.parseInt(idTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            VentanaRegistroPedido.this,
                            "El ID debe ser un número.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                Pedido pedido = new Pedido(
                        id,
                        direccion,
                        EstadoPedido.PENDIENTE
                );

                controlador.agregarPedido(pedido);

                JOptionPane.showMessageDialog(
                        VentanaRegistroPedido.this,
                        "Pedido registrado correctamente.\n"
                                + "ID: " + id
                                + "\nDirección: " + direccion
                                + "\nTipo: " + tipo,
                        "Pedido registrado",
                        JOptionPane.INFORMATION_MESSAGE
                );

                txtId.setText("");
                txtDireccion.setText("");
            }
        });
    }
}