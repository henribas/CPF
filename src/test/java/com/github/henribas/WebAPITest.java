package com.github.henribas;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class WebAPITest {
    
    @Test
    void testHello() {
        given()
        .when().get("/api")
        .then()
           .statusCode(200)
           .body(is("Hello from CPF API"));
    }

}
