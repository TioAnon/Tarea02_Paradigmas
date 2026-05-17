package cl.unab.tarea2.backend;

import cl.unab.tarea2.backend.exceptions.FormatoArchivoException;
import cl.unab.tarea2.events.EventBus;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorEjercicios {
    private List<Ejercicio> catalogoBase;

    public GestorEjercicios() {
        this.catalogoBase = new ArrayList<>();
    }

    public void cargarDatos(String ruta) {
        try {
            this.catalogoBase = CargadorEjercicios.cargar(ruta);
            EventBus.getInstance().notificarCargaCompleta(this.catalogoBase);
        } catch (FileNotFoundException e) {
            EventBus.getInstance().notificarError("Error de Persistencia: Archivo inexistente en la ruta " + ruta);
        } catch (IOException e) {
            EventBus.getInstance().notificarError("Error de Entrada/Salida de datos: " + e.getMessage());
        } catch (FormatoArchivoException e) {
            EventBus.getInstance().notificarError("Error de Formato de Archivo: " + e.getMessage());
        }
    }

    public void generarRutina(int cantCardio, int cantFuerza, NivelIntensidad intensidadRequerida) {
        Rutina rutina = new Rutina();
        int cardioAgregados = 0;
        int fuerzaAgregados = 0;

        for (Ejercicio ej : catalogoBase) {
            boolean esSemanaValida = (ej.getUltimaSemanaUsado() == 0);
            boolean coincideIntensidad = (ej.getIntensidad() == intensidadRequerida);

            if (coincideIntensidad && esSemanaValida) {
                if (ej.getTipo() == TipoEjercicio.CARDIOVASCULAR && cardioAgregados < cantCardio) {
                    rutina.agregarEjercicio(ej);
                    cardioAgregados++;
                } else if (ej.getTipo() == TipoEjercicio.FUERZA && fuerzaAgregados < cantFuerza) {
                    rutina.agregarEjercicio(ej);
                    fuerzaAgregados++;
                }
            }
        }

        if (cardioAgregados < cantCardio || fuerzaAgregados < cantFuerza) {
            EventBus.getInstance().notificarError("Error de Generación: Ejercicios insuficientes en base de datos para cubrir la cantidad solicitada con intensidad " + intensidadRequerida.getEtiqueta());
        } else {
            EventBus.getInstance().notificarRutinaGenerada(rutina);
        }
    }
}