package demo.phorest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
/**
 * Swagger configuration
 */
@Configuration
@EnableOpenApi
public class SwaggerConfiguration {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo()).select()
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Phorest Demo PawelS")
                .description("Salon Manager description")
                .build();
    }

}
