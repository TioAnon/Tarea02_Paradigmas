package cl.unab.tarea2.backend;

import java.util.ArrayList;
import java.util.List;

public class Rutina {
    private final List<Ejercicio> ejercicios;

    public Rutina() {
        this.ejercicios = new ArrayList<>();
    }

    public void agregarEjercicio(Ejercicio ej) {
        this.ejercicios.add(ej);
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public int getCantidadTotal() {
        return ejercicios.size();
    }

    public int calcularTiempoTotal() {
        int total = 0;
        for (Ejercicio e : ejercicios) {
            total += e.getTiempoEstimado();
        }
        return total;
    }

    public int contarPorTipo(TipoEjercicio tipo) {
        int cont = 0;
        for (Ejercicio e : ejercicios) {
            if (e.getTipo() == tipo) cont++;
        }
        return cont;
    }

    public int contarPorIntensidad(NivelIntensidad intensidad) {
        int cont = 0;
        for (Ejercicio e : ejercicios) {
            if (e.getIntensidad() == intensidad) cont++;
        }
        return cont;
    }
}