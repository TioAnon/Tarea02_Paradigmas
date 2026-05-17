import cl.unab.tarea2.frontend.VentanaPrincipal;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Encapsulación de la GUI en el Event Dispatch Thread (EDT) para estabilidad asrincrónica
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true); // Mutación de estado del componente a visible
        });
    }
}