package cl.unab.tarea2.frontend;

import cl.unab.tarea2.backend.*;
import cl.unab.tarea2.events.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaGeneracion extends JFrame implements RutinaListener {
    private final GestorEjercicios gestor;
    private JTextField txtCardio, txtFuerza;
    private JComboBox<NivelIntensidad> cbIntensidad;

    public VentanaGeneracion(GestorEjercicios gestor) {
        this.gestor = gestor;
        EventBus.getInstance().suscribir(this);

        setTitle("Configuración de Rutina");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel(" Cantidad Cardiovascular:"));
        txtCardio = new JTextField("0"); add(txtCardio);

        add(new JLabel(" Cantidad Fuerza:"));
        txtFuerza = new JTextField("0"); add(txtFuerza);

        add(new JLabel(" Nivel de Intensidad:"));
        cbIntensidad = new JComboBox<>(NivelIntensidad.values()); add(cbIntensidad);

        JButton btnGenerar = new JButton("Construir Rutina");
        add(new JLabel()); add(btnGenerar);

        btnGenerar.addActionListener(e -> {
            try {
                int cardio = Integer.parseInt(txtCardio.getText().trim());
                int fuerza = Integer.parseInt(txtFuerza.getText().trim());
                NivelIntensidad intensidad = (NivelIntensidad) cbIntensidad.getSelectedItem();
                gestor.generarRutina(cardio, fuerza, intensidad);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Debe ingresar números enteros válidos.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    @Override
    public void onCargaCompleta(List<Ejercicio> ejercicios) {}

    @Override
    public void onRutinaGenerada(Rutina rutina) {
        new VentanaRevision(rutina).setVisible(true);
        dispose();
    }

    @Override
    public void onError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error en Generación", JOptionPane.ERROR_MESSAGE);
    }
}