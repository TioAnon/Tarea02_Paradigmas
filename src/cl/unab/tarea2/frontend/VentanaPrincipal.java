package cl.unab.tarea2.frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        EstiloUI.aplicar();
        setTitle("Sistema de Rutinas de Entrenamiento — UNAB");
        setSize(480, 340);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel raiz = new JPanel(new BorderLayout());
        raiz.setBackground(EstiloUI.GRIS_FONDO);
        setContentPane(raiz);

        // Encabezado con degradado verde
        raiz.add(EstiloUI.panelEncabezado(
                "Sistema de Rutinas de Entrenamiento",
                "Centro Deportivo UNAB — Gestión personalizada",
                EstiloUI.VERDE_MEDIO), BorderLayout.NORTH);

        // Panel central
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBackground(EstiloUI.GRIS_FONDO);
        centro.setBorder(new EmptyBorder(36, 48, 28, 48));

        JLabel lblBienvenida = new JLabel("Bienvenido al sistema");
        lblBienvenida.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblBienvenida.setForeground(EstiloUI.TEXTO_OSCURO);
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblDesc = new JLabel(
                "<html><center>Diseñe rutinas de entrenamiento personalizadas<br>" +
                        "para sus clientes según sus objetivos físicos.</center></html>");
        lblDesc.setFont(EstiloUI.FUENTE_CAMPO);
        lblDesc.setForeground(EstiloUI.GRIS_TEXTO);
        lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnIniciar = EstiloUI.botonPrimario("  ▶  Ingresar al sistema", EstiloUI.VERDE_MEDIO);
        btnIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIniciar.setPreferredSize(new Dimension(240, 44));
        btnIniciar.setMaximumSize(new Dimension(240, 44));

        btnIniciar.addActionListener(e -> {
            new VentanaCarga().setVisible(true);
            dispose();
        });

        centro.add(lblBienvenida);
        centro.add(Box.createVerticalStrut(10));
        centro.add(lblDesc);
        centro.add(Box.createVerticalStrut(28));
        centro.add(btnIniciar);

        raiz.add(centro, BorderLayout.CENTER);

        // Pie
        JLabel pie = new JLabel("Versión 2.0  •  UNAB", JLabel.CENTER);
        pie.setFont(new Font("SansSerif", Font.PLAIN, 10));
        pie.setForeground(EstiloUI.GRIS_TEXTO);
        pie.setBorder(new EmptyBorder(0, 0, 10, 0));
        raiz.add(pie, BorderLayout.SOUTH);
    }
}