========================================================================
   ARCHIVO LÉAME - SISTEMA DE GESTIÓN DE RUTINAS DE ENTRENAMIENTO
========================================================================

1. INFORMACIÓN INSTITUCIONAL Y DE IDENTIFICACIÓN
------------------------------------------------------------------------
Universidad: Universidad Andrés Bello
Facultad: Facultad de Ingeniería
Carrera: Ingeniería en Computación e Informática
Asignatura: Paradigmas de Programación (PTEC102)
NRC: 8025
Docente: Juan Calderón Maureira
Estudiante: Diego Ignacio Rubilar Gómez
Fecha de Entrega: Mayo de 2026

2. INSTRUCCIONES DE DESPLIEGUE Y EJECUCIÓN
------------------------------------------------------------------------
Para inicializar el sistema correctamente en la Máquina Virtual de Java 
(JVM), siga estrictamente el siguiente protocolo:

1. Importar el proyecto completo en un IDE compatible (IntelliJ IDEA 
   altamente recomendado para reconocer los archivos de configuración .iml).
2. Asegurar el uso de un JDK compatible (desarrollado y testeado bajo JDK 25).
3. Verificar la persistencia física: El archivo con el catálogo de datos 
   DEBE estar ubicado en la ruta relativa 'data/ejercicios.txt' respecto a 
   la raíz del espacio de trabajo.
4. Compilar y ejecutar la clase 'Main.java' ubicada en la raíz del 
   directorio de fuentes (src/).

3. ORGANIZACIÓN Y FORMATO DEL ARCHIVO DE DATOS (ejercicios.txt)
------------------------------------------------------------------------
El motor de persistencia parsea el archivo de texto de forma secuencial 
línea por línea. Cada registro debe contener exactamente 7 atributos 
delimitados obligatoriamente por un punto y coma (;):

Formato:
codigo;nombre;tipo;intensidad;tiempo_estimado;descripcion;semana_ultimo_uso

Campos válidos aceptados por el deserializador:
- tipo: 'Cardiovascular' o 'Fuerza' (Insensible a mayúsculas/minúsculas).
- intensidad: 'Básico', 'Intermedio', 'Avanzado', 'Alto rendimiento'.
- tiempo_estimado: Valor numérico entero positivo en minutos.
- semana_ultimo_uso: Entero (0 = Disponible, >0 = Usado previamente).

Ejemplo de registro óptimo:
E001;Trote suave;Cardiovascular;Básico;30;Trote constante a ritmo bajo;0

4. ALCANCES Y SUPUESTOS DEL SISTEMA (REGLAS DE NEGOCIO)
------------------------------------------------------------------------
- Validación de Excepciones: El Cargador valida estructuralmente tres 
  anomalías críticas mediante excepciones controladas:
    * Archivo Inexistente (FileNotFoundException).
    * Información Incompleta (Campos insuficientes por línea o vacíos).
    * Formato Incorrecto (Fallo de parseo tipográfico o numérico).
- Restricción de No Repetición Semanal: El algoritmo del Gestor de 
  Ejercicios discrimina y descarta de forma automatizada cualquier registro 
  cuyo atributo 'semana_ultimo_uso' sea mayor que 0. Esto asegura que no 
  se repitan ejercicios utilizados en semanas consecutivas.
- Robustez del Interfaz (UX): Los botones de navegación de la interfaz 
  gobiernan dinámicamente sus estados. El botón 'Volver' se deshabilita 
  en el índice 0 para evitar desbordamientos. El botón 'Siguiente' transmuta 
  su etiqueta y lógica a 'Resumen de la rutina' al alcanzar el último nodo.

5. ARQUITECTURA DE SOFTWARE Y PATRONES DISEÑADOS
------------------------------------------------------------------------
La solución se encuentra estrictamente desacoplada y modularizada en 
capas independientes para asegurar alta cohesión y bajo acoplamiento:

- cl.unab.tarea2.backend: Contiene los modelos orientados a objetos, 
  tipos enumerados (Enums) y los motores de carga y cálculo de datos.
- cl.unab.tarea2.backend.exceptions: Aloja las excepciones personalizadas 
  diseñadas para el control de flujo y errores.
- cl.unab.tarea2.events: Paquete encargado de la programación orientada 
  a eventos. Implementa el patrón Notificación-Suscripción (Observer) 
  mediante una interfaz listener y un distribuidor de eventos (EventBus) 
  centralizado con patrón Singleton para la comunicación asincrónica.
- cl.unab.tarea2.frontend: Capa visual independiente construida sobre 
  Java Swing, compuesta por 5 instancias autónomas de interfaz de usuario.
========================================================================