package ar.com.cdt.demoConsola.service;

import ar.com.cdt.demoConsola.repository.IPersonaRepo;
import ar.com.cdt.demoConsola.repository.PersonaRepoImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements IPersonaService{

    @Autowired
    @Qualifier("Implementacion1")
    IPersonaRepo repo;
    
    @Override
    public void registrar(String name) {
        //repo = new PersonaRepoImpl1(); //Aclaro que implementacion usa
        repo.registrar(name);
    }
    
}
