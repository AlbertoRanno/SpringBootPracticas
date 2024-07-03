package ar.com.cdt.demoWeb.repo;

import ar.com.cdt.demoWeb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {
    
}
