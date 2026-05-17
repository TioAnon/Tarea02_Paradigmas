package cl.unab.tarea2.backend;

public abstract class Ejercicio {
    protected String codigo;
    protected String nombre;
    protected TipoEjercicio tipo;
    protected NivelIntensidad intensidad;
    protected int tiempoEstimado;
    protected String descripcionEjecucion;
    protected int ultimaSemanaUsado;

    public Ejercicio(String codigo, String nombre, TipoEjercicio tipo, NivelIntensidad intensidad, 
                     int tiempo, String descripcion, int semana) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.intensidad = intensidad;
        this.tiempoEstimado = tiempo;
        this.descripcionEjecucion = descripcion;
        this.ultimaSemanaUsado = semana;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public TipoEjercicio getTipo() { return tipo; }
    public NivelIntensidad getIntensidad() { return intensidad; }
    public int getTiempoEstimado() { return tiempoEstimado; }
    public String getDescripcionEjecucion() { return descripcionEjecucion; }
    public int getUltimaSemanaUsado() { return ultimaSemanaUsado; }
}