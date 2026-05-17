package cl.unab.tarea2.events;

import cl.unab.tarea2.backend.Ejercicio;
import cl.unab.tarea2.backend.Rutina;
import java.util.ArrayList;
import java.util.List;

public class EventBus {
    private static EventBus instancia;
    private final List<RutinaListener> suscriptores;

    private EventBus() {
        this.suscriptores = new ArrayList<>();
    }

    public static synchronized EventBus getInstance() {
        if (instancia == null) {
            instancia = new EventBus();
        }
        return instancia;
    }

    public void suscribir(RutinaListener suscriptor) {
        this.suscriptores.add(suscriptor);
    }

    public void notificarCargaCompleta(List<Ejercicio> ejercicios) {
        for (RutinaListener s : suscriptores) {
            s.onCargaCompleta(ejercicios);
        }
    }

    public void notificarRutinaGenerada(Rutina rutina) {
        for (RutinaListener s : suscriptores) {
            s.onRutinaGenerada(rutina);
        }
    }

    public void notificarError(String mensaje) {
        for (RutinaListener s : suscriptores) {
            s.onError(mensaje);
        }
    }
}