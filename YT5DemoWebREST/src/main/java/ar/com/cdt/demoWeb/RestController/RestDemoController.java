package ar.com.cdt.demoWeb.RestController;

import ar.com.cdt.demoWeb.model.Persona;
import ar.com.cdt.demoWeb.repo.IPersonaRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
public class RestDemoController {

    @Autowired
    private IPersonaRepo repo;

    @GetMapping
    public List<Persona> listar() {
        return repo.findAll();
    }

    @PostMapping
    public void insertar(@RequestBody Persona persona) {
        repo.save(persona);
    }

    @PutMapping
    public void modificar(@RequestBody Persona persona) {
        repo.save(persona);
    }

    @DeleteMapping(value = "/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        repo.deleteById(id);
    }
}


/* Construyo API REST:
- nuevo paquete para un Controlador, pero esta vez del tipo REST y no del tipo WEB. 
(Nota: OJO que sea un subpaquete del principal, porque sino no lo toma)    
- @RestController indica que voy a construir una app del tipo REST
- @RequestMapping("/nombreRutaPrincipal") es una buena practica, sino tomaría la raiz ("/")
- Al omitir la capa de Service, importo la interfaz de la capa de datos, con @Autowired para que maneja en automatico
la creacion e implementacion de la instancia (solo una, asi que no hace falta el @Qualifier)
- Como toda REST tiene que atender a los metodos GET POST PUT DELETE del protocolo HTTP. En consecuencia uso @GetMapping, @PostMapp...
- Get: por la ruta indicada antes, si consulto por Get (navegador o Postman), quiero que devuelva una lista de los objetos
del tipo Persona. Estos objetos los va a listar en formato JSON, para que puedan ser consumidos por navegador o Front
- Post: ya no por navegador, sino por Postman. El metodo es del tipo void porque no retorna nada, sino que recibe. @RequestBody
es para que interprete el JSON que viene en el body del request (Body - RAW - JSON - En Postman), y lo transforme a un objeto del
tipo JAVA. el metodo save viene de los default de la interface IPersonaRepo por extender de JpaRepository
- Put: es para una actualizacion completa de los datos. (sino es PATCH para uno o varios atributos). Se usa el mismo 
metodo save, porque va a mirar el ID, si ese ID no existe aun, hara una insersion, pero si ve que existe, actualizara
todos sus datos por los que recibe. 
(Nota: Por eso el metodo save, tanto con Post como Put, requiere de un objeto Persona del cual obtener los datos)
- Delete: en cambio, no requiere todos los datos del objeto, sino su ID, esto porque elegí el metodo por default "deleteById".
Por lo que necesita saber de manera inequivoca, que objeto borrar. El ID, se va a parar por la url (y no el body del request
como en el post y put). Aclaro partes del codigo: 
    - el (value = "/{id}") define la url a la que se debe hacer la solicitud, en este caso quedaría: /personas/{id}
    - @PathVariable("id") indica que Spring Boot tomará el valor de id de la URL y lo pasará como argumento al método eliminar.
    - Integer id: Es el tipo de dato del parámetro id
    - repo.deleteById(id): Elimina la persona de la base de datos que tiene el identificador id proporcionado.
 */
