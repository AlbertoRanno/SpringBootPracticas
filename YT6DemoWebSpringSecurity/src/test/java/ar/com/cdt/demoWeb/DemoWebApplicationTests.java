package ar.com.cdt.demoWeb;

import ar.com.cdt.demoWeb.model.Usuario;
import ar.com.cdt.demoWeb.repo.IUsuarioRepo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/*Agrego Spring Security al POM - Protege sin modificar la logica de negocio
Con solo agregarlo al POM ya protege al proyecto. Agrega pantalla de Login
A priori pasa un password en la consola, y el usuario siempre es 'user'.
Esta config por default, se puede moficiar desde el Properties:
spring.security.user.name = admin
spring.security.user.password = 321
Pero esto no se usa... los usuarios se guardan en base de datos, y se va a enlazar para corroborar credenciales y roles.
Entonces:
Creo Tabla de Usuario: nuevo Modelo => Clase => @Entity => Atributos => @Id => metodos get y set
    Si no indico por @Column el nombre de la columna, va a tomar por default el nombre del atributo
Como le digo a Spring Security que en esta tabla van a estar los usuarios? 
-"Podria abrir el PGAdmin (estimos se refiere a la BBDD) y crear un usuario y contraseña y empezar a crear unos datos por defecto" pero no...
- (opta por hacer una) Prueba unitaria: A tal fin, crea IUsuarioRepo, y vengo a acá, a Test Packages, a a crear las pruebas unitarias:

Aqui se usa la libreria de JUnit: */

@SpringBootTest
public class DemoWebApplicationTests {

    @Autowired
    private IUsuarioRepo repo;
    
    @Autowired
    private BCryptPasswordEncoder encoder; /*(***)*/
    
    //Prueba Unitaria: Pensarlas como "Qué parametros de entrada voy a tener, y que voy a esperar que devuelva"
	@Test
	public void crearUsuarioTest() {
            Usuario us = new Usuario();
            //Parametros de entrada:
            us.setId(2);
            us.setNombre("Polaco2");
            us.setClave(encoder.encode("123"));
            Usuario retorno = repo.save(us); //Para esto creé el IUsuarioRepo, y hago el @Autowired aqui
            
            //Lo que se espera que devuelva: (para ser considerado una prueba debe devolver V o F)
            assertTrue(retorno.getClave().equalsIgnoreCase(us.getClave()));
            /* "Verificacion: Lo que retorna el usuarioInsertado ("retorno"), su clave, la que se guardo en base de datos,
             es igual, a la clave que estoy mandando acá?*/
            
            /* Para correr la prueba: click derecho => 'Test File'
            Al correrla, ya guarda al usuario en la BBDD. Si queres probar cambia el ID, nombre y clave.
            Para que las Claves no se guardan asi planas... se encryptan con BCrypt
            Para eso, requiero un Bean => Lo creo => SecurityConfig en el paquete principal
            Y Mediante un @Autowired me lo traigo a esta clase (***)
            Ojo que no este corriendo la app de fondo
            Pude probar el Login, y funciona, pero sin la app corriendo, accedo a la ruta, pero no hay nada levantado.
            
            Video 10:
            Vuelvo a SecurityConfig y le agrego una anotacion para habilitarlo*/
            
	}

}