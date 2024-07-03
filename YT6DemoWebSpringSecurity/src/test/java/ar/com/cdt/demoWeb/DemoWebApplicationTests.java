package ar.com.cdt.demoWeb;

import ar.com.cdt.demoWeb.model.Usuario;
import ar.com.cdt.demoWeb.repo.IUsuarioRepo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class DemoWebApplicationTests {

    @Autowired
    private IUsuarioRepo repo;
    
    @Autowired
    private BCryptPasswordEncoder encoder;
    
	@Test
	public void crearUsuarioTest() {
            Usuario us = new Usuario();
            us.setId(4);
            us.setNombre("Alber");
            us.setClave(encoder.encode("111"));
            Usuario retorno = repo.save(us);
            
            assertTrue(retorno.getClave().equalsIgnoreCase(us.getClave()));
	}

}
