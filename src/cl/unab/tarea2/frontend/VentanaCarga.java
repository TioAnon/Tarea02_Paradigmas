package cl.unab.tarea2.frontend;

import cl.unab.tarea2.backend.*;
import cl.unab.tarea2.events.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.List;

public class VentanaCarga extends JFrame implements RutinaListener {

    private final GestorEjercicios gestor;

    // Refs a los labels de las tarjetas para actualizarlos
    private JLabel vTotal, vTiempo, vCardio, vFuerza;
    private JLabel vBasico, vIntermedio, vAvanzado, vAlto;

    private JLabel lblArchivo;
    private JButton btnSiguiente;
    private JButton btnSeleccionar;

    public VentanaCarga() {
        this.gestor = new GestorEjercicios();
        EventBus.getInstance().suscribir(this);

        setTitle("Carga de Ejercicios");
        setSize(700, 540);
        setMinimumSize(new Dimension(640, 480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel raiz = new JPanel(new BorderLayout());
        raiz.setBackground(EstiloUI.GRIS_FONDO);
        setContentPane(raiz);

        raiz.add(EstiloUI.panelEncabezado(
                "Carga de ejercicios",
                "Seleccione el archivo de datos para inicializar el catálogo",
                EstiloUI.AZUL_MEDIO), BorderLayout.NORTH);

        raiz.add(construirCentro(), BorderLayout.CENTER);
        raiz.add(construirPie(),   BorderLayout.SOUTH);
    }

    // ── Panel central ────────────────────────────────────────────────────────

    private JPanel construirCentro() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(EstiloUI.GRIS_FONDO);
        p.setBorder(new EmptyBorder(20, 24, 16, 24));

        // Selector de archivo
        p.add(panelSelector());
        p.add(Box.createVerticalStrut(22));

        // Título sección estadísticas
        JLabel lbl = new JLabel("Estadísticas del catálogo cargado");
        lbl.setFont(EstiloUI.FUENTE_SUBTITULO);
        lbl.setForeground(EstiloUI.GRIS_TEXTO);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(lbl);
        p.add(Box.createVerticalStrut(10));

        // Grid 2×2 de tarjetas (totales)
        JPanel gridTotales = new JPanel(new GridLayout(1, 2, 12, 0));
        gridTotales.setOpaque(false);
        gridTotales.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        gridTotales.setAlignmentX(Component.LEFT_ALIGNMENT);

        vTotal  = EstiloUI.tarjetaStat(gridTotales, "—", "Total ejercicios",    EstiloUI.VERDE_MEDIO)[0];
        vTiempo = EstiloUI.tarjetaStat(gridTotales, "—", "Tiempo total (min)",  EstiloUI.AZUL_MEDIO)[0];
        p.add(gridTotales);
        p.add(Box.createVerticalStrut(10));

        JPanel gridTipos = new JPanel(new GridLayout(1, 2, 12, 0));
        gridTipos.setOpaque(false);
        gridTipos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        gridTipos.setAlignmentX(Component.LEFT_ALIGNMENT);

        vCardio = EstiloUI.tarjetaStat(gridTipos, "—", "Cardiovascular",  EstiloUI.CARDIO_COLOR)[0];
        vFuerza = EstiloUI.tarjetaStat(gridTipos, "—", "Fuerza",          EstiloUI.FUERZA_COLOR)[0];
        p.add(gridTipos);
        p.add(Box.createVerticalStrut(16));

        // Niveles
        JLabel lblNiv = new JLabel("Por nivel de intensidad");
        lblNiv.setFont(EstiloUI.FUENTE_SUBTITULO);
        lblNiv.setForeground(EstiloUI.GRIS_TEXTO);
        lblNiv.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(lblNiv);
        p.add(Box.createVerticalStrut(10));

        JPanel gridNiveles = new JPanel(new GridLayout(1, 4, 10, 0));
        gridNiveles.setOpaque(false);
        gridNiveles.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        gridNiveles.setAlignmentX(Component.LEFT_ALIGNMENT);

        Color[] coloresNiv = {
                new Color(0x33691E), new Color(0x1565C0),
                new Color(0x6A1B9A), new Color(0xB71C1C)
        };
        vBasico      = EstiloUI.tarjetaStat(gridNiveles, "—", "Básico",           coloresNiv[0])[0];
        vIntermedio  = EstiloUI.tarjetaStat(gridNiveles, "—", "Intermedio",        coloresNiv[1])[0];
        vAvanzado    = EstiloUI.tarjetaStat(gridNiveles, "—", "Avanzado",          coloresNiv[2])[0];
        vAlto        = EstiloUI.tarjetaStat(gridNiveles, "—", "Alto rendimiento",  coloresNiv[3])[0];
        p.add(gridNiveles);

        return p;
    }

    private JPanel panelSelector() {
        JPanel p = new JPanel(new BorderLayout(10, 0));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(EstiloUI.GRIS_BORDE, 1, true),
                new EmptyBorder(12, 16, 12, 16)));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        p.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblArchivo = new JLabel("Ningún archivo seleccionado");
        lblArchivo.setFont(EstiloUI.FUENTE_CAMPO);
        lblArchivo.setForeground(EstiloUI.GRIS_TEXTO);

        btnSeleccionar = EstiloUI.botonPrimario("Examinar...", EstiloUI.AZUL_MEDIO);
        btnSeleccionar.setPreferredSize(new Dimension(130, 34));

        btnSeleccionar.addActionListener(e -> abrirSelector());

        p.add(lblArchivo, BorderLayout.CENTER);
        p.add(btnSeleccionar, BorderLayout.EAST);
        return p;
    }

    private JPanel construirPie() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(EstiloUI.GRIS_FONDO);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, EstiloUI.GRIS_BORDE),
                new EmptyBorder(12, 24, 12, 24)));

        btnSiguiente = EstiloUI.botonPrimario("Generar rutina  →", EstiloUI.VERDE_MEDIO);
        btnSiguiente.setEnabled(false);
        btnSiguiente.addActionListener(e -> {
            new VentanaGeneracion(gestor).setVisible(true);
            dispose();
        });

        p.add(btnSiguiente, BorderLayout.EAST);
        return p;
    }

    // ── Lógica ───────────────────────────────────────────────────────────────

    private void abrirSelector() {
        JFileChooser fc = new JFileChooser(new File("."));
        fc.setDialogTitle("Seleccionar archivo de ejercicios");
        fc.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt, *.csv)", "txt", "csv"));
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            lblArchivo.setText(f.getName());
            lblArchivo.setForeground(EstiloUI.TEXTO_OSCURO);
            gestor.cargarDatos(f.getAbsolutePath());
        }
    }

    // ── RutinaListener ───────────────────────────────────────────────────────

    @Override
    public void onCargaCompleta(List<Ejercicio> ejercicios) {
        int tiempo = 0, cC = 0, cF = 0, cB = 0, cI = 0, cA = 0, cAlt = 0;
        for (Ejercicio e : ejercicios) {
            tiempo += e.getTiempoEstimado();
            if (e.getTipo() == TipoEjercicio.CARDIOVASCULAR) cC++; else cF++;
            switch (e.getIntensidad()) {
                case BASICO:           cB++;   break;
                case INTERMEDIO:       cI++;   break;
                case AVANZADO:         cA++;   break;
                case ALTO_RENDIMIENTO: cAlt++; break;
            }
        }
        vTotal.setText(String.valueOf(ejercicios.size()));
        vTiempo.setText(tiempo + " min");
        vCardio.setText(String.valueOf(cC));
        vFuerza.setText(String.valueOf(cF));
        vBasico.setText(String.valueOf(cB));
        vIntermedio.setText(String.valueOf(cI));
        vAvanzado.setText(String.valueOf(cA));
        vAlto.setText(String.valueOf(cAlt));
        btnSiguiente.setEnabled(true);
    }

    @Override public void onRutinaGenerada(Rutina rutina) {}

    @Override
    public void onError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}