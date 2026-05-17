package cl.unab.tarea2.backend;

import cl.unab.tarea2.backend.exceptions.FormatoArchivoException;

public enum NivelIntensidad {
    BASICO("Básico"),
    INTERMEDIO("Intermedio"),
    AVANZADO("Avanzado"),
    ALTO_RENDIMIENTO("Alto rendimiento");

    private final String etiqueta;

    NivelIntensidad(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public static NivelIntensidad deserializar(String texto) throws FormatoArchivoException {
        if (texto == null) {
            throw new FormatoArchivoException("Nivel de intensidad nulo.");
        }
        String limpiado = texto.trim().toLowerCase()
                .replace("básico", "basico")
                .replace("alto rendimiento", "alto rendimiento");

        switch (limpiado) {
            case "basico":
                return BASICO;
            case "intermedio":
                return INTERMEDIO;
            case "avanzado":
                return AVANZADO;
            case "alto rendimiento":
                return ALTO_RENDIMIENTO;
            default:
                throw new FormatoArchivoException("Nivel de intensidad desconocido: " + texto);
        }
    }
}