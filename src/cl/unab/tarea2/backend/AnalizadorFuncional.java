package cl.unab.tarea2.backend;

import java.util.List;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

public class AnalizadorFuncional {
    public static List<Resumen> generarResumenPorCurso(List<Tarea> listaTareas) {
        return listaTareas.stream()
                .collect(Collectors.groupingBy(
                        Tarea::getCurso,
                        Collectors.summarizingDouble(Tarea::getNota)
                ))
                .entrySet()
                .stream()
                .map(entry -> new Resumen(
                        entry.getKey(),
                        entry.getValue().getAverage(),
                        entry.getValue().getCount()
                ))
                .toList();
    }
}