package com.biblioteca.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Library API",
                version = "1.0",
                description = "Bookstore management REST API",
                contact = @Contact(
                        name = "Alexandre Silva",
                        email = "alexandresargitario@gmail.com"
                ),
                license = @License(
                        name = "MTI"
                )

        )
)
public class OpenApiConfig {
}
