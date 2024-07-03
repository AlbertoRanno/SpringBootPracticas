package ar.com.cdt.demoWeb.repo;

import ar.com.cdt.demoWeb.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonaRepo extends JpaRepository<Persona, Integer> {
    
}
