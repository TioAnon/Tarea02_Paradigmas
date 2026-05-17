package cl.unab.tarea2.backend;

import cl.unab.tarea2.backend.exceptions.FormatoArchivoException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CargadorEjercicios {

    public static List<Ejercicio> cargar(String ruta) throws FileNotFoundException, IOException, FormatoArchivoException {
        List<Ejercicio> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            int nroLinea = 0;

            while ((linea = br.readLine()) != null) {
                nroLinea++;
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(";");

                if (datos.length < 7) {
                    throw new FormatoArchivoException("Informacion incompleta en linea " + nroLinea + ": " + linea);
                }

                String codigo = datos[0].trim();
                String nombre = datos[1].trim();
                String tipoTexto = datos[2].trim();
                String intensidadTexto = datos[3].trim();

                int tiempo;
                int semana;

                try {
                    tiempo = Integer.parseInt(datos[4].trim());
                    semana = Integer.parseInt(datos[6].trim());
                } catch (NumberFormatException e) {
                    throw new FormatoArchivoException("Formato numerico incorrecto en linea " + nroLinea);
                }

                String descripcion = datos[5].trim();

                TipoEjercicio tipo = TipoEjercicio.deserializar(tipoTexto);
                NivelIntensidad intensidad = NivelIntensidad.deserializar(intensidadTexto);

                if (tipo == TipoEjercicio.CARDIOVASCULAR) {
                    lista.add(new EjercicioCardiovascular(codigo, nombre, intensidad, tiempo, descripcion, semana));
                } else {
                    lista.add(new EjercicioFuerza(codigo, nombre, intensidad, tiempo, descripcion, semana));
                }
            }
        }
        return lista;
    }
}