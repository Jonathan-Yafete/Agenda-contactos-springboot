package com.agenda.contactos.Agenda_contactos_springboot;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Sistema de Agenda de Contactos") // Reemplaza "OpenAPI definition"
                        .version("1.0.0")
                        .description("Documentación interactiva de los endpoints REST para la gestión de contactos vinculados a la base de datos MySQL.")
                        .contact(new Contact()
                                .name("Jonathan Yafete") // Tu nombre de desarrollador
                                .email("jonathan@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")));
    }
}
