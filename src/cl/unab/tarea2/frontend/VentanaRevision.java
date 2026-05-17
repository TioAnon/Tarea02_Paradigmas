package cl.unab.tarea2.frontend;

import cl.unab.tarea2.backend.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaRevision extends JFrame {
    private final Rutina rutina;
    private final List<Ejercicio> ejercicios;
    private int indiceActual = 0;

    private JLabel lblNombre, lblTipo, lblIntensidad, lblTiempo;
    private JTextArea txtDescripcion;
    private JButton btnVolver, btnSiguiente;

    public VentanaRevision(Rutina rutina) {
        this.rutina = rutina;
        this.ejercicios = rutina.getEjercicios();

        setTitle("Revisión de Rutina Dinámica");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelDatos = new JPanel(new GridLayout(4, 2, 5, 5));
        panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelDatos.add(new JLabel("Nombre:")); lblNombre = new JLabel(); panelPanel(lblNombre, panelDatos);
        panelDatos.add(new JLabel("Tipo:")); lblTipo = new JLabel(); panelPanel(lblTipo, panelDatos);
        panelDatos.add(new JLabel("Intensidad:")); lblIntensidad = new JLabel(); panelPanel(lblIntensidad, panelDatos);
        panelDatos.add(new JLabel("Tiempo Estimado:")); lblTiempo = new JLabel(); panelPanel(lblTiempo, panelDatos);

        txtDescripcion = new JTextArea();
        txtDescripcion.setEditable(false);
        txtDescripcion.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(txtDescripcion);
        scroll.setBorder(BorderFactory.createTitledBorder("Descripción de Ejecución"));

        JPanel panelNavegacion = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnVolver = new JButton("Volver");
        btnSiguiente = new JButton("Siguiente");
        panelNavegacion.add(btnVolver);
        panelNavegacion.add(btnSiguiente);

        add(panelDatos, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelNavegacion, BorderLayout.SOUTH);

        btnVolver.addActionListener(e -> {
            if (indiceActual > 0) {
                indiceActual--;
                actualizarVista();
            }
        });

        btnSiguiente.addActionListener(e -> {
            if (btnSiguiente.getText().equals("Resumen de la rutina")) {
                new VentanaResumen(rutina).setVisible(true);
                dispose();
            } else {
                if (indiceActual < ejercicios.size() - 1) {
                    indiceActual++;
                    actualizarVista();
                }
            }
        });

        actualizarVista();
    }

    private void panelPanel(JLabel label, JPanel panel) {
        label.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(label);
    }

    private void actualizarVista() {
        if (ejercicios.isEmpty()) return;

        Ejercicio ej = ejercicios.get(indiceActual);
        lblNombre.setText(ej.getNombre());
        lblTipo.setText(ej.getTipo().getDescripcion());
        lblIntensidad.setText(ej.getIntensidad().getEtiqueta());
        lblTiempo.setText(ej.getTiempoEstimado() + " minutos");
        txtDescripcion.setText(ej.getDescripcionEjecucion());

        // Control estricto de estados exigidos por la Rúbrica
        btnVolver.setEnabled(indiceActual != 0);

        if (indiceActual == ejercicios.size() - 1) {
            btnSiguiente.setText("Resumen de la rutina");
        } else {
            btnSiguiente.setText("Siguiente");
        }
    }
}