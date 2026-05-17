package cl.unab.tarea2.frontend;

import cl.unab.tarea2.backend.*;
import javax.swing.*;
import java.awt.*;

public class VentanaResumen extends JFrame {
    public VentanaResumen(Rutina rutina) {
        setTitle("Resumen Final de la Rutina");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Estadísticas Generadas", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JPanel panelCalculos = new JPanel(new GridLayout(7, 2, 5, 5));
        panelCalculos.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panelCalculos.add(new JLabel("Cardiovasculares:"));
        panelCalculos.add(new JLabel(String.valueOf(rutina.contarPorTipo(TipoEjercicio.CARDIOVASCULAR))));

        panelCalculos.add(new JLabel("Fuerza:"));
        panelCalculos.add(new JLabel(String.valueOf(rutina.contarPorTipo(TipoEjercicio.FUERZA))));

        panelCalculos.add(new JLabel("Ejercicios Básicos:"));
        panelCalculos.add(new JLabel(String.valueOf(rutina.contarPorIntensidad(NivelIntensidad.BASICO))));

        panelCalculos.add(new JLabel("Ejercicios Intermedios:"));
        panelCalculos.add(new JLabel(String.valueOf(rutina.contarPorIntensidad(NivelIntensidad.INTERMEDIO))));

        panelCalculos.add(new JLabel("Ejercicios Avanzados:"));
        panelCalculos.add(new JLabel(String.valueOf(rutina.contarPorIntensidad(NivelIntensidad.AVANZADO))));

        panelCalculos.add(new JLabel("Alto Rendimiento:"));
        panelCalculos.add(new JLabel(String.valueOf(rutina.contarPorIntensidad(NivelIntensidad.ALTO_RENDIMIENTO))));

        panelCalculos.add(new JLabel("Tiempo Total Rutina:"));
        JLabel lblTotalTime = new JLabel(rutina.calcularTiempoTotal() + " min");
        lblTotalTime.setForeground(Color.BLUE);
        lblTotalTime.setFont(new Font("Arial", Font.BOLD, 12));
        panelCalculos.add(lblTotalTime);

        JButton btnFinalizar = new JButton("Cerrar Aplicación");

        add(lblTitulo, BorderLayout.NORTH);
        add(panelCalculos, BorderLayout.CENTER);
        add(btnFinalizar, BorderLayout.SOUTH);

        btnFinalizar.addActionListener(e -> System.exit(0));
    }
}