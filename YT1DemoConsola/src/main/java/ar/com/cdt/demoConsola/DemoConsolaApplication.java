package ar.com.cdt.demoConsola;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // marca la clase como la principal para una aplicación Spring Boot
public class DemoConsolaApplication implements CommandLineRunner {

    /* Interfaz: Define métodos que una clase debe implementar.
    Implementación: Proporciona el comportamiento para los métodos definidos en la interfaz.
    CommandLineRunner: Te permite ejecutar código al inicio de una aplicación Spring Boot, ideal para aplicaciones de consola.
    Si hago ctrl + click en CommandLineRunner, veo un solo metodo, el run, el cual en este archivo implemento.
    Si no implementara esta interfaz, los mensajes en consola solo podria enviarlos antes del metodo run de la app (***) donde 
    quizas no todo lo que necesito de Spring esté cargado. Ya que si envio los SOUT luego del metodo run de la app (***), la misma
    app bloqueará ese hilo. En cambio, al implementar el run de la interfaz, puedo correrlos, ni bien todos los componentes se hayan
    cargado por completo.*/
    // Para este proyecto no seleccioné ninguna dependencia adicional. En el POM solo están las que vienen por defecto:
    // - spring-boot-starter (el core, lo básico: Tomcat embebido, validaciones, etc.)
    // - spring-boot-starter-test (para pruebas unitarias)
    /* Variable para manejar los logs de esta clase. Nota: importar desde org.slf4j.Logger y org.slf4j.LoggerFactory 
    Defino un logger para manejar los mensajes de log. Esto es preferible a usar System.out.println porque es más eficiente y flexible */
    private static Logger LOG = LoggerFactory.getLogger(DemoConsolaApplication.class);
    /* Private determina que la variable LOG es solo accesible desde esta clase. 
    La idea es que cada clase tenga su propia variable para manejar los logs, 
    lo que permite identificar claramente su origen.
    Static determina que este atributo es de la clase en sí misma y no de sus instancias individuales. 
    Esto significa que todas las instancias de la clase compartirán la misma variable LOG para el registro de logs. */

    // Este es el punto de entrada de la aplicación Spring Boot. SpringApplication.run inicializa y ejecuta la aplicación.
    public static void main(String[] args) {
        SpringApplication.run(DemoConsolaApplication.class, args);
    }

    // Implemento el método run de la interfaz CommandLineRunner. (Ojo que no estoy sobreescribiendo el run de la app) 
    // Este método se ejecutará después de que el contexto de Spring Boot se haya inicializado.
    @Override
    public void run(String... args) throws Exception {
        // Puedo enviar mensajes a la consola con System.out.println, pero no es lo más eficiente.
        // Es mejor usar un LOGGER para manejar los mensajes de log.
        System.out.println("Puedo enviar mensajes a consola por aquí, pero no es lo más eficiente, lo mejor es un LOGGER");

        // Usando el LOGGER para enviar un mensaje informativo.
        LOG.info("Mensaje gracias a la variable LOG, que maneja los LOGs para la clase indicada. \n"
                + "Es más elegante y maneja mejor el rendimiento que el System.out.println");

        // Usando el LOGGER para enviar un mensaje de advertencia.
        LOG.warn("Al hacer LOG. , van a salir todas las variantes que se pueden usar");

        // Nota: En algunas consolas, los mensajes de advertencia pueden no aparecer en un color diferente.
        /* Hay varias razones por las que usar un sistema de logging como SLF4J con Logback (o cualquier otro framework de logging)
        es más eficiente y flexible que System.out.println:

        Nivel de Logs: Los sistemas de logging permiten configurar diferentes niveles de logs (INFO, DEBUG, WARN, ERROR, etc.), 
        lo que facilita el control de la cantidad de información que se registra en diferentes entornos (desarrollo, pruebas, producción).

        Redirección de Salida: Puedes redirigir la salida de los logs a diferentes destinos (consola, archivos, sistemas de monitoreo)
        sin cambiar el código. Con System.out.println, todos los mensajes van a la consola, lo que no siempre es deseable.

        Configuración Dinámica: Los frameworks de logging permiten configurar y cambiar dinámicamente los comportamientos de logging
        sin modificar el código. Puedes ajustar los niveles de log y los destinos de manera sencilla mediante archivos de configuración
        (como logback.xml).

        Rendimiento: System.out.println es una operación de E/S (entrada/salida) que puede ser lenta y bloquear el hilo de ejecución.
        Los frameworks de logging están optimizados para minimizar el impacto en el rendimiento, usando buffers y otros mecanismos
        para gestionar la salida de manera más eficiente.

        Formato y Contexto: Los sistemas de logging permiten añadir contexto adicional a los mensajes de log (como timestamps, 
        nombres de hilos, nombres de clases, etc.) y formatear los mensajes de manera consistente.*/
    }
}
