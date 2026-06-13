package cl.unab.tarea2.backend;

public class Tarea {
    private final String curso;
    private final double nota;

    public Tarea(String curso, double nota) {
        this.curso = curso;
        this.nota = nota;
    }

    public String getCurso() {
        return curso;
    }

    public double getNota() {
        return nota;
    }
}