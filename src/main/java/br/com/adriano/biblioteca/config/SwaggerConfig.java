package br.com.adriano.biblioteca.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Biblioteca")
                        .description("API REST para gerenciamento de livros e categorias")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Adriano Barbosa")
                                .email("adrianobarbosa_4@hotmail.com"))
                        .license(new License()
                                .name("MIT")));
    }
}
