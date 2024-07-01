package ar.com.cdt.demoWeb.controller;

import ar.com.cdt.demoWeb.model.Persona;
import ar.com.cdt.demoWeb.repo.IPersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @Autowired
    private IPersonaRepo repo;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {

        Persona p = new Persona();
        p.setIdPersona(2);
        p.setNombre("Juana");
        repo.save(p);

        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/listar")
    public String greeting(Model model) {

        model.addAttribute("personas", repo.findAll());
        return "greeting";
    }

    /* Sumo las dependencias de:
        - Spring Data JPA - para simplificar las conexiones a la BBDD (VER EL PROPERTIES que hice un mix, con una nueva base "demo")
        - MySQL driver porque es el motor de BBDD que quiero usar, en el video ven otro, pero el código que escriba es independiente
    a la bbdd que elija..
        
    Creo los paquetes:
        - model - para entidades, mis representaciones de las tablas (con los getters and setters es suficiente, pero podria usar
        Lombok / recordar los @Entity - @Id - @Column(name="")
        - repo - conexiones a la bbdd (interfaces => Las implementaciones se las dejo automáticas y de haber varias uso Qualifier)
    
    Omito en este caso la capa de service e inyecto la interface de IPersonaRepo, con la anotacion de Autowired la dejo que 
    la implementacion se cree automatica. Y al haber solo 1, no hace falta el Qualifier.
    
    Creo una persona en el controlador, y utilizo el metodo por default de la interfaz de la que extendí, para guardarla.
    (Luego buscar diferencias entre extender de CrudRepository y JpaRepository)
    
    Luego las conexiones a la BBDD se setean en el Properties. Solo necesité esas propiedades y QUE EL SCHEMA DE LA BBDD
    ESTÉ CREADO! (Fui al workbench y creé un nuevo schema "demo", y lo dejé así, sin tablas ni nada. La tabla de Persona,
    se creó al correr la app... ver en el controlador que si por el navegador entraba por /greeting, guardaba, y si entraba
    por /listar, me mostraba las inserciones
     */
}
