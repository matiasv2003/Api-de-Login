package Api.de.Login.Api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Login - Level Up Gamer")
                        .version("1.0")
                        .description("Documentación de la API para login, usuarios y autenticación"));
    }
}

//http://localhost:8080/swagger-ui/index.html