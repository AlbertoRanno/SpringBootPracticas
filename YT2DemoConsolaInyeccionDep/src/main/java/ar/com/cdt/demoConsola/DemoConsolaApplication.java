package ar.com.cdt.demoConsola;

import ar.com.cdt.demoConsola.service.IPersonaService;
import ar.com.cdt.demoConsola.service.PersonaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoConsolaApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(DemoConsolaApplication.class);
    
    @Autowired
    private IPersonaService service;

    public static void main(String[] args) {
        SpringApplication.run(DemoConsolaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //LOG.info("Mensaje info importante");
        //LOG.warn("Mensaje advertencia");
        //LOG.error("Mensaje error");
        
        //service = new PersonaServiceImpl(); //Aclaro que implementacion usa (**)
        service.registrar("Alberto Daniel Ranno");

        /* En este proyecto hago que viaje, mi nombre, del controlador (Vista), a la capa de servicio, a la capa de datos.
        De manera muy simplificada, pero usando siempre interfaces, de modo de que mi codigo sea altamente desacoplable. 
        La idea es ver el tema de inyeccion de dependencias. Para que viaje correctamente, en el controlador, que es el 
        punto de entrada de la info, importo la interfaz de Servicios, le aclaro que implementacion de la misma quiero usar (**),
        con eso, la info (mi nombre) viaja a esa implementacion. En ella, importo la interfaz del repositorio, e indico, al igual
        que acá (**) que implementacion de esa interfaz quiero usar. Con esa aclaracion, mi nombre viaja a PersonaRepoImpl,
        es tomado por su metodo registrar, y se muestra el LOG 
        Ahora, lo importante, es que gracias a Spring, no hace falta indicar de esta manera (**) que implementacion de la interfaz
        quiero usar, sino que se maneja de la siguiente manera:
        -A las clases que implementan interfaces, se les agrega la notacion de stereotype, a las de la capa de datos "@repository",
        a las de servicio "@service", y a los controladores "@Controller" (Web - Front) o "@RestController" (API - endpoints). Se les
        podría agregar el mismo estereotipo a todas, porque seria suficiente para Spring saber que clase es un stereotype, para de
        la misma clase, crear las instancias, pero es una mala práctica (por ejemplo, en lugar de @Repository, ponerle tambien, @Service).
        Por lo que es más claro, y buena práctica segun las normas de Spring, aplicarle a cada una la correspondiente.
        -Cuando les agrego las notaciones, Spring ya se va a encargar solo de crear las instancias de manera automatica, donde se necesite.
        Por ejemplo, en este caso, ya no voy a necesitar de aclarar que quiero la implementacion (**), la va a crear, y tomar esa en 
        automatico. Si es que solo tuviera esa. Si hubiera mas de 1, se usa otra anotacion, la cual me va a permitir, indicar cuales
        dentro de varias implementaciones para una misma interfaz quiero.
        
        -Agregue otra implementacion de la interfaz IPersonaRepo en la capa de datos. Esto podria servir para conectar a distintas
        bases de datos (cada implementacion a una distinta y las switcheo a voluntad)
        -Al tener mas de una implementacion, toma importancia la notacion de @Qualifier("nombreIdentificatorio") para identificar cada una.
        Agrego esta notacion en los lugares claves: 
        1-debajo de la anotacion de stereoType (por ejemplo, debajo de @Repository si tengo mas de una en la capa de datos, o debajo
        de @Service, si tengo mas de una en la capa de Negocios). Ahi sería para darles el nombre a cada implementacion.
        2-debajo del @Autowired, en la clase donde la esté llamando, para indicar cual, de entre todas las que haya, quiero.
        */
        
    }
}
