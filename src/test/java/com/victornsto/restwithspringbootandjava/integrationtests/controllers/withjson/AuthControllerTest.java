package com.victornsto.restwithspringbootandjava.integrationtests.controllers.withjson;

import com.victornsto.restwithspringbootandjava.config.TestConfigs;
import com.victornsto.restwithspringbootandjava.integrationtests.dto.AccountCredentialsDto;
import com.victornsto.restwithspringbootandjava.integrationtests.dto.TokenDto;
import com.victornsto.restwithspringbootandjava.integrationtests.testcontainers.AbstractIntagrationTest;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthControllerTest extends AbstractIntagrationTest {

    private static TokenDto tokenDto;

    @BeforeAll
    static void setUp() {
        tokenDto = new TokenDto();
    }

    @Test
    @Order(1)
    void singIn() {
        AccountCredentialsDto credentials = new AccountCredentialsDto();
        credentials.setUsername("leandro");
        credentials.setPassword("admin123");
        tokenDto = given()
                .basePath("/auth/signin")
                .port(TestConfigs.SERVER_PORT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(credentials)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(TokenDto.class);
        assertNotNull(tokenDto.getAcessToken());
        assertNotNull(tokenDto.getRefreshToken());
    }

    @Test
    @Order(2)
    void refreshToken() {
        System.out.println(tokenDto.getRefreshToken());
        tokenDto = given()
                .basePath("/auth/refresh/")
                .port(TestConfigs.SERVER_PORT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("username", tokenDto.getUsername())
                .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + tokenDto.getRefreshToken())
                .when()
                .put("{username}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(TokenDto.class);
        assertNotNull(tokenDto.getAcessToken());
        assertNotNull(tokenDto.getRefreshToken());
    }
}