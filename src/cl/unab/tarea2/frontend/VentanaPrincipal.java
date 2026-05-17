package cl.unab.tarea2.frontend;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setTitle("Gestión de Rutinas - UNAB");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        JLabel lblTitulo = new JLabel("Sistema de Rutinas Personalizadas", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnIniciar = new JButton("Ingresar al Sistema");
        btnIniciar.setPreferredSize(new Dimension(180, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(10, 10, 20, 10);
        add(lblTitulo, gbc);

        gbc.gridy = 1;
        add(btnIniciar, gbc);

        btnIniciar.addActionListener(e -> {
            new VentanaCarga().setVisible(true);
            dispose();
        });
    }
}