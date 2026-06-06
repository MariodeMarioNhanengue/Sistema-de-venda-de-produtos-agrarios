package com.ujc.students.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        final String schemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Venda Interprovincial de Produtos Agrários")
                        .description("""
                                API REST para gestão de venda interprovincial de produtos agrários em Moçambique.

                                ## Autenticação
                                A API utiliza **JWT (Bearer Token)**. Para aceder aos endpoints protegidos:
                                1. Faça login em `POST /auth/login` para obter o token.
                                2. Clique em **Authorize** e cole o token no campo `bearerAuth`.

                                ## Perfis de Acesso
                                | Perfil       | Permissões principais                                     |
                                |--------------|-----------------------------------------------------------|
                                | `ADMIN`      | Acesso total a todos os recursos                          |
                                | `AGRICULTOR` | Gerir produtos, consultar e actualizar pedidos/entregas    |
                                | `COMPRADOR`  | Gerir compradores, criar pedidos e entregas                |
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("UJC Students")
                                .email("suporte@ujc.ac.mz")))
                .addSecurityItem(new SecurityRequirement().addList(schemeName))
                .components(new Components()
                        .addSecuritySchemes(schemeName, new SecurityScheme()
                                .name(schemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Insira o token JWT obtido em POST /auth/login")));
    }
}
