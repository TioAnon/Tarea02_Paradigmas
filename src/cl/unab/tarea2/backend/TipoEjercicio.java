package cl.unab.tarea2.backend;

import cl.unab.tarea2.backend.exceptions.FormatoArchivoException;

public enum TipoEjercicio {
    CARDIOVASCULAR("Cardiovascular"),
    FUERZA("Fuerza");

    private final String descripcion;

    TipoEjercicio(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoEjercicio deserializar(String texto) throws FormatoArchivoException {
        if (texto == null) {
            throw new FormatoArchivoException("Tipo de ejercicio nulo.");
        }
        switch (texto.trim().toLowerCase()) {
            case "cardiovascular":
            case "cardio":
                return CARDIOVASCULAR;
            case "fuerza":
                return FUERZA;
            default:
                throw new FormatoArchivoException("Tipo de ejercicio desconocido: " + texto);
        }
    }
}