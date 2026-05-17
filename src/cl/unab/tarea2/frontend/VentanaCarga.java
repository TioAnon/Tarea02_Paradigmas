package cl.unab.tarea2.frontend;

import cl.unab.tarea2.backend.*;
import cl.unab.tarea2.events.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class VentanaCarga extends JFrame implements RutinaListener {
    private final GestorEjercicios gestor;
    private JLabel lblTotal, lblTiempo, lblCardio, lblFuerza, lblBasico, lblIntermedio, lblAvanzado, lblAlto;
    private JButton btnSiguiente;

    public VentanaCarga() {
        this.gestor = new GestorEjercicios();
        EventBus.getInstance().suscribir(this);

        setTitle("Carga de Datos");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JButton btnSeleccionar = new JButton("Seleccionar Archivo de Ejercicios");
        JPanel panelInfo = new JPanel(new GridLayout(8, 2, 5, 5));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Estadísticas del Catálogo"));

        panelInfo.add(new JLabel(" Total Ejercicios:")); lblTotal = new JLabel("-"); panelInfo.add(lblTotal);
        panelInfo.add(new JLabel(" Tiempo Disponible:")); lblTiempo = new JLabel("-"); panelInfo.add(lblTiempo);
        panelInfo.add(new JLabel(" Ejercicios Cardio:")); lblCardio = new JLabel("-"); panelInfo.add(lblCardio);
        panelInfo.add(new JLabel(" Ejercicios Fuerza:")); lblFuerza = new JLabel("-"); panelInfo.add(lblFuerza);
        panelInfo.add(new JLabel(" Intensidad Básica:")); lblBasico = new JLabel("-"); panelInfo.add(lblBasico);
        panelInfo.add(new JLabel(" Intensidad Intermedia:")); lblIntermedio = new JLabel("-"); panelInfo.add(lblIntermedio);
        panelInfo.add(new JLabel(" Intensidad Avanzada:")); lblAvanzado = new JLabel("-"); panelInfo.add(lblAvanzado);
        panelInfo.add(new JLabel(" Alto Rendimiento:")); lblAlto = new JLabel("-"); panelInfo.add(lblAlto);

        btnSiguiente = new JButton("Comenzar Generación de Rutina");
        btnSiguiente.setEnabled(false);

        add(btnSeleccionar, BorderLayout.NORTH);
        add(panelInfo, BorderLayout.CENTER);
        add(btnSiguiente, BorderLayout.SOUTH);

        btnSeleccionar.addActionListener(e -> {
            JFileChooser fc = new JFileChooser(new File("."));
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                gestor.cargarDatos(fc.getSelectedFile().getAbsolutePath());
            }
        });

        btnSiguiente.addActionListener(e -> {
            new VentanaGeneracion(gestor).setVisible(true);
            dispose();
        });
    }

    @Override
    public void onCargaCompleta(List<Ejercicio> ejercicios) {
        lblTotal.setText(String.valueOf(ejercicios.size()));
        int tiempo = 0, cCardio = 0, cFuerza = 0, cBas = 0, cInt = 0, cAv = 0, cAlto = 0;

        for (Ejercicio e : ejercicios) {
            tiempo += e.getTiempoEstimado();
            if (e.getTipo() == TipoEjercicio.CARDIOVASCULAR) cCardio++; else cFuerza++;
            if (e.getIntensidad() == NivelIntensidad.BASICO) cBas++;
            else if (e.getIntensidad() == NivelIntensidad.INTERMEDIO) cInt++;
            else if (e.getIntensidad() == NivelIntensidad.AVANZADO) cAv++;
            else cAlto++;
        }

        lblTiempo.setText(tiempo + " min");
        lblCardio.setText(String.valueOf(cCardio));
        lblFuerza.setText(String.valueOf(cFuerza));
        lblBasico.setText(String.valueOf(cBas));
        lblIntermedio.setText(String.valueOf(cInt));
        lblAvanzado.setText(String.valueOf(cAv));
        lblAlto.setText(String.valueOf(cAlto));
        btnSiguiente.setEnabled(true);
    }

    @Override
    public void onRutinaGenerada(Rutina rutina) {}

    @Override
    public void onError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error Detectado", JOptionPane.ERROR_MESSAGE);
    }
}