package ar.com.cdt.demoConsola.repository;

import ar.com.cdt.demoConsola.DemoConsolaApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("Implementacion2")
public class PersonaRepoImpl2 implements IPersonaRepo{
    
    private static Logger LOG = LoggerFactory.getLogger(DemoConsolaApplication.class);

    @Override
    public void registrar(String name) {
        LOG.info("Se registró a " + name + " en implementacion 2");
    }
    
}
