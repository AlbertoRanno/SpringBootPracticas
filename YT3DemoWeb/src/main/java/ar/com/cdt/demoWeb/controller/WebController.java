package ar.com.cdt.demoWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    
    @GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
        
        /* En este proyecto, utilizo las librerías: Web, Thymeleaf, devtools y copio el código de la documentacion de Spring:
        https://spring.io/guides/gs/serving-web-content 
        Web se utiliza para el modelo MVC, Thymeleaf para motor de plantillas html, y devtools para reiniciar al guardar.
        Al agregar la notacion de Controlador, controlador es una clase del modelo MVC, que se encarga de redireccionar las 
        peticiones del usuario (get - post - put - delete) a alguna lógica de negocio (capa de servicio).
        -@GetMapping("/greeting") me indica que voy a poder entrar a la logica debajo (public String greeting(@Reques...) si
        por el navegador (GET), accedo por Get a esa ruta (/greeting)
        -@RequestParam(name="name", required=false, defaultValue="World") al poner @RequestParam(name="name", me habilita
        a poner en la ruta, un parametro del tipo name, por ejemplo: ?name=Cacho => http://localhost:8080/greeting?name=Cacho. 
        required=false, hace que no sea obligario. Y defaultValue="World", hace que si no paso ninguno, porque no es obligatorio,
        el valor por default sea "World". Y este valor, "Cacho" que viaja, se guarda en la variable  String name.
        Model model es propio de Spring MVC, y se usa para agregar las variables que necesite para agregarlas en las vistas (html)
        mediante: model.addAttribute("name", name);
        return "greeting"; devuelve en la ruta indicada, el html dentro de templates, (config de Spring Boot al ver Thymeleaf), que 
        coincide con ese nombre. Obs. Obviamente el nombre de la ruta no necesariamente tiene que ser igual al de la pagina de html.
        -En el html, reemplacé el código por el de la documentacion, sigo alli los comentarios.
        */
}
