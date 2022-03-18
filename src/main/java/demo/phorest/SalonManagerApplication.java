package demo.phorest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * SalonManagerApplication entry class
 */
@SpringBootApplication
public class SalonManagerApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SalonManagerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SalonManagerApplication.class, args);
    }

}
