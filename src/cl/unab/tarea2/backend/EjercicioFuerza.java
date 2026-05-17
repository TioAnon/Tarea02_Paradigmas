package cl.unab.tarea2.backend;

public class EjercicioFuerza extends Ejercicio {
    public EjercicioFuerza(String codigo, String nombre, NivelIntensidad intensidad, 
                           int tiempo, String descripcion, int semana) {
        super(codigo, nombre, TipoEjercicio.FUERZA, intensidad, tiempo, descripcion, semana);
    }
}