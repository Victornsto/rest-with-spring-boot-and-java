package com.victornsto.restwithspringbootandjava.integrationtests.controllers.withjson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victornsto.restwithspringbootandjava.config.TestConfigs;
import com.victornsto.restwithspringbootandjava.integrationtests.dto.PersonDto;
import com.victornsto.restwithspringbootandjava.integrationtests.testcontainers.AbstractIntagrationTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.util.Date;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonControllerTest extends AbstractIntagrationTest {

    private static RequestSpecification spec;
    private static ObjectMapper mapper;
    private static PersonDto personDto;
    @BeforeAll
    static void setUp() {
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        personDto = new PersonDto();
    }

    @Test
    void findAll() {
        mockPerson();
    }



    @Test
    @Order(1)
    void create() throws JsonProcessingException {
        mockPerson();
        spec = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCAL)
                .setBasePath("/person")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(personDto)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        PersonDto createdPerson = mapper.readValue(content, PersonDto.class);
        personDto = createdPerson;
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());
        assertTrue(createdPerson.getId() > 0);

        assertEquals("John", createdPerson.getFirstName());
        assertEquals("Doe", createdPerson.getLastName());
        assertEquals("123 Main St", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
    }

    @Test
    @Order(2)
    void createWithWrongOrigin() {
        mockPerson();
        spec = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_SEMERU)
                .setBasePath("/person")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(personDto)
                .when()
                .post()
                .then()
                .statusCode(403)
                .extract()
                .body()
                .asString();
        assertEquals("Invalid CORS request", content);
    }

    @Test
    @Order(3)
    void findById() throws JsonProcessingException {
        spec = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCAL)
                .setBasePath("/person")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("id", personDto.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        PersonDto createdPerson = mapper.readValue(content, PersonDto.class);
        personDto = createdPerson;
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());
        assertTrue(createdPerson.getId() > 0);

        assertEquals("John", createdPerson.getFirstName());
        assertEquals("Doe", createdPerson.getLastName());
        assertEquals("123 Main St", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
    }

    @Test
    @Order(4)
    void findByIdWithWrongOrigin() throws JsonProcessingException {
        spec = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_SEMERU)
                .setBasePath("/person")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("id", personDto.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(403)
                .extract()
                .body()
                .asString();
        assertEquals("Invalid CORS request", content);
    }


    @Test
    @Order(5)
    void update() throws JsonProcessingException {
        spec = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCAL)
                .setBasePath("/person")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(personDto)
                .when()
                .put()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        PersonDto createdPerson = mapper.readValue(content, PersonDto.class);
        personDto = createdPerson;
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());
        assertTrue(createdPerson.getId() > 0);

        assertEquals("John", createdPerson.getFirstName());
        assertEquals("Doe", createdPerson.getLastName());
        assertEquals("123 Main St", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
    }

    @Test
    @Order(6)
    void updateWithWrongOrigin() throws JsonProcessingException {
        spec = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_SEMERU)
                .setBasePath("/person")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(personDto)
                .when()
                .put()
                .then()
                .statusCode(403)
                .extract()
                .body()
                .asString();
        assertEquals("Invalid CORS request", content);
    }

    @Test
    void delete() {
        spec = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCAL)
                .setBasePath("/person")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

       given(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("id", personDto.getId())
                .when()
                .delete("{id}")
                .then()
                .statusCode(204);
    }

    private void mockPerson() {
        personDto.setFirstName("John");
        personDto.setLastName("Doe");
        personDto.setAddress("123 Main St");
        personDto.setGender("Male");
    }
}