package cl.unab.tarea2.frontend;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Clase de utilidades de estilo compartidas por todas las ventanas del frontend.
 * Centraliza colores, fuentes y fábricas de componentes para coherencia visual.
 */
public final class EstiloUI {

    private EstiloUI() {}

    // ── Paleta ──────────────────────────────────────────────────────────────
    public static final Color VERDE_OSCURO   = new Color(0x1B5E20);
    public static final Color VERDE_MEDIO    = new Color(0x2E7D32);
    public static final Color VERDE_CLARO    = new Color(0x43A047);
    public static final Color VERDE_FONDO    = new Color(0xE8F5E9);

    public static final Color AZUL_OSCURO    = new Color(0x0D47A1);
    public static final Color AZUL_MEDIO     = new Color(0x1565C0);
    public static final Color AZUL_CLARO     = new Color(0xBBDEFB);

    public static final Color NARANJA_ACENTO = new Color(0xE65100);
    public static final Color NARANJA_CLARO  = new Color(0xFFF3E0);

    public static final Color GRIS_FONDO     = new Color(0xF5F5F5);
    public static final Color GRIS_BORDE     = new Color(0xDDDDDD);
    public static final Color GRIS_TEXTO     = new Color(0x616161);
    public static final Color TEXTO_OSCURO   = new Color(0x212121);
    public static final Color BLANCO         = Color.WHITE;

    public static final Color CARDIO_COLOR   = new Color(0x1565C0);
    public static final Color FUERZA_COLOR   = new Color(0xBF360C);

    // ── Fuentes ─────────────────────────────────────────────────────────────
    public static final Font FUENTE_TITULO   = new Font("SansSerif", Font.BOLD, 18);
    public static final Font FUENTE_SUBTITULO= new Font("SansSerif", Font.BOLD, 13);
    public static final Font FUENTE_CAMPO    = new Font("SansSerif", Font.PLAIN, 13);
    public static final Font FUENTE_VALOR    = new Font("SansSerif", Font.BOLD, 26);
    public static final Font FUENTE_ETIQUETA = new Font("SansSerif", Font.PLAIN, 11);
    public static final Font FUENTE_BOTON    = new Font("SansSerif", Font.BOLD, 13);

    // ── Look & Feel ─────────────────────────────────────────────────────────
    public static void aplicar() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        UIManager.put("OptionPane.messageFont", FUENTE_CAMPO);
        UIManager.put("OptionPane.buttonFont",  FUENTE_BOTON);
    }

    // ── Fábricas de componentes ─────────────────────────────────────────────

    /** Botón con fondo de color y texto blanco. */
    public static JButton botonPrimario(String texto, Color fondo) {
        JButton b = new JButton(texto) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color base = isEnabled() ? fondo : GRIS_BORDE;
                g2.setColor(getModel().isPressed()  ? base.darker()  :
                        getModel().isRollover() ? base.brighter(): base);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        b.setFont(FUENTE_BOTON);
        b.setForeground(BLANCO);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setOpaque(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(180, 38));
        return b;
    }

    /** Botón secundario con borde. */
    public static JButton botonSecundario(String texto) {
        JButton b = new JButton(texto);
        b.setFont(FUENTE_BOTON);
        b.setForeground(GRIS_TEXTO);
        b.setBackground(BLANCO);
        b.setFocusPainted(false);
        b.setBorder(new CompoundBorder(
                new LineBorder(GRIS_BORDE, 1, true),
                new EmptyBorder(6, 16, 6, 16)));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(140, 38));
        return b;
    }

    /**
     * Tarjeta de estadística: número grande arriba, etiqueta abajo.
     * Devuelve el JLabel del número para que pueda actualizarse.
     */
    public static JLabel[] tarjetaStat(JPanel contenedor, String valor, String etiqueta, Color acento) {
        JPanel card = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BLANCO);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.setColor(acento);
                g2.fillRoundRect(0, 0, 4, getHeight(), 4, 4);
                g2.setColor(GRIS_BORDE);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 12, 12);
                g2.dispose();
            }
        };
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(14, 18, 14, 14));

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(FUENTE_VALOR);
        lblValor.setForeground(acento);
        lblValor.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblEtiqueta = new JLabel(etiqueta);
        lblEtiqueta.setFont(FUENTE_ETIQUETA);
        lblEtiqueta.setForeground(GRIS_TEXTO);
        lblEtiqueta.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(lblValor);
        card.add(Box.createVerticalStrut(3));
        card.add(lblEtiqueta);

        contenedor.add(card);
        return new JLabel[]{lblValor, lblEtiqueta};
    }

    /** Panel encabezado con degradado de color. */
    public static JPanel panelEncabezado(String titulo, String subtitulo, Color colorBase) {
        JPanel p = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setPaint(new GradientPaint(0, 0, colorBase, getWidth(), 0, colorBase.darker()));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        p.setOpaque(false);
        p.setBorder(new EmptyBorder(20, 24, 20, 24));

        JLabel lTitulo = new JLabel(titulo);
        lTitulo.setFont(FUENTE_TITULO);
        lTitulo.setForeground(BLANCO);

        JPanel textos = new JPanel();
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.setOpaque(false);
        textos.add(lTitulo);

        if (subtitulo != null && !subtitulo.isEmpty()) {
            JLabel lSub = new JLabel(subtitulo);
            lSub.setFont(FUENTE_ETIQUETA);
            lSub.setForeground(new Color(255,255,255,180));
            textos.add(Box.createVerticalStrut(4));
            textos.add(lSub);
        }

        p.add(textos, BorderLayout.WEST);
        return p;
    }

    /** Separador horizontal delgado. */
    public static JSeparator separador() {
        JSeparator s = new JSeparator();
        s.setForeground(GRIS_BORDE);
        s.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        return s;
    }

    /** Fila campo — valor usada en VentanaRevision. */
    public static JPanel filaCampoValor(String campo, String valor) {
        JPanel f = new JPanel(new BorderLayout(8, 0));
        f.setBackground(BLANCO);
        f.setBorder(new EmptyBorder(7, 0, 7, 0));

        JLabel lCampo = new JLabel(campo);
        lCampo.setFont(FUENTE_SUBTITULO);
        lCampo.setForeground(GRIS_TEXTO);
        lCampo.setPreferredSize(new Dimension(150, 20));

        JLabel lValor = new JLabel(valor);
        lValor.setFont(FUENTE_CAMPO);
        lValor.setForeground(TEXTO_OSCURO);

        f.add(lCampo, BorderLayout.WEST);
        f.add(lValor, BorderLayout.CENTER);
        return f;
    }

    /** Badge pill de color (tipo/nivel). */
    public static JLabel badge(String texto, Color fondo, Color texto2) {
        JLabel l = new JLabel(texto) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(fondo);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        l.setFont(new Font("SansSerif", Font.BOLD, 11));
        l.setForeground(texto2);
        l.setBorder(new EmptyBorder(3, 12, 3, 12));
        l.setOpaque(false);
        return l;
    }
}