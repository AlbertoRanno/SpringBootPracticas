package ar.com.cdt.demoWeb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration //Anotacion correspondiente de Stereotype, para que Spring maneje en su contenedor, los Bean, que aqui cree..
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //Segun video: WebSecurityConfigurerAdapter

    @Bean //Para que pueda ser llamado desde otra capa por un @Autowired
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

}