package cl.unab.tarea2.events;

import cl.unab.tarea2.backend.Ejercicio;
import cl.unab.tarea2.backend.Rutina;
import java.util.List;

public interface RutinaListener {
    void onCargaCompleta(List<Ejercicio> ejercicios);
    void onRutinaGenerada(Rutina rutina);
    void onError(String mensaje);
}