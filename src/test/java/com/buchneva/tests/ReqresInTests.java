package com.buchneva.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;


public class ReqresInTests {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in/api";
    }


    @Test
    void checkSingleResourceNotFound() {

        given()
                .log().uri()
                .when()
                .get("/unknown/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void checkSingleResource() {

        given()
                .log().uri()
                .when()
                .get("/unknown/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2),
                        "data.name", is("fuchsia rose"),
                        "data.year", is(2001),
                        "data.color", is("#C74375"),
                        "data.pantone_value", is("17-2031"))
                .body("support.url", is("https://reqres.in/#support-heading"),
                        "support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    @Test
    void checkCreateWithStatus() {

        String data = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .get("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    void checkCreate() {

        String data = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"),
                        "job", is("leader"));
    }

    @Test
    void loginUnsuccessful() {

        String data = "{ \"email\": \"peter@klaven\" }";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
