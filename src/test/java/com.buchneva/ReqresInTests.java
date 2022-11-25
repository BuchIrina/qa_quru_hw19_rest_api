package com.buchneva;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;


public class ReqresInTests {


    @Test
    void checkSingleResourceNotFound() {

        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void checkSingleResourceName() {

        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.name", is("fuchsia rose"));
    }

    @Test
    void checkCreateWithStatus() {

        String data = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    void checkCreateJob() {

        String data = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("job", is("leader"));
    }

    @Test
    void loginUnsuccessful() {

        String data = "{ \"email\": \"peter@klaven\" }";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
