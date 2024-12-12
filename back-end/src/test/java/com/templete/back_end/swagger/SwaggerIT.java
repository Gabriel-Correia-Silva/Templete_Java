package com.templete.back_end.swagger;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class SwaggerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Deve carregar a interface Swagger UI com sucesso")
    public void deveCarregarSwaggerUI() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/swagger-ui/index.html")
                .then()
                .statusCode(200)
                .body(containsString("Swagger UI"));
    }

    @Test
    @DisplayName("Deve verificar se todos os endpoints estão documentados no Swagger")
    public void deveConterTodosEndpointsNoSwagger() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/v3/api-docs")
                .then()
                .statusCode(200)
                .body("paths", hasKey("/api/login"))
                .body("paths", hasKey("/api/register"))
                .body("paths", hasKey("/Usuarios"))
                .body("paths", hasKey("/Usuarios/{id}"))
                .body("paths", hasKey("/Usuarios/Cadastro"));
    }

    @Test
    @DisplayName("Deve verificar se todos os esquemas estão documentados no Swagger")
    public void deveConterTodosEsquemasNoSwagger() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/v3/api-docs")
                .then()
                .statusCode(200)
                .body("components.schemas", hasKey("AuthenticationDTO"))
                .body("components.schemas", hasKey("RegisterDTO"))
                .body("components.schemas", hasKey("GrantedAuthority"))
                .body("components.schemas", hasKey("Usuario"));
    }

    @Test
    @DisplayName("Deve verificar se os endpoints retornam conteúdo não vazio")
    public void deveRetornarConteudoNaoVazio() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/v3/api-docs")
                .then()
                .statusCode(200)
                .body("paths", not(empty()))
                .body("components.schemas", not(empty()));
    }
}
