package cl.unab.tarea2.backend;

public class EjercicioCardiovascular extends Ejercicio {
    public EjercicioCardiovascular(String codigo, String nombre, NivelIntensidad intensidad, 
                                   int tiempo, String descripcion, int semana) {
        super(codigo, nombre, TipoEjercicio.CARDIOVASCULAR, intensidad, tiempo, descripcion, semana);
    }
}