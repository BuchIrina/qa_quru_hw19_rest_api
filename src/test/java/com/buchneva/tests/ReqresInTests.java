package com.buchneva.tests;

import com.buchneva.models.lombok.CreateModel;
import com.buchneva.models.lombok.LoginBodyLombokModel;
import com.buchneva.models.lombok.LoginResponseLombokModel;
import org.junit.jupiter.api.Test;

import static com.buchneva.specs.RequestSpecs.requestSpec;
import static com.buchneva.specs.ResponseSpecs.responseSpec;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;


public class ReqresInTests {
    @Test
    void checkSingleResourceNotFound() {

        given()
                .spec(requestSpec)
                .when()
                .get("/unknown/23")
                .then()
                .spec(responseSpec)
                .statusCode(404);
    }

    @Test
    void checkSingleResource() {

        given()
                .spec(requestSpec)
                .when()
                .get("/unknown/2")
                .then()
                .spec(responseSpec)
                .body("data.id", is(2),
                        "data.name", is("fuchsia rose"),
                        "data.year", is(2001),
                        "data.color", is("#C74375"),
                        "data.pantone_value", is("17-2031"))
                .body("support.url", is("https://reqres.in/#support-heading"),
                        "support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!"));

    }

    @Test
    void checkCreate() {
        CreateModel data = new CreateModel();
        data.setName("Irina");
        data.setJob("qa");

        given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec)
                .statusCode(201)
                .body("name", is(data.getName()),
                        "job", is(data.getJob()));
    }

    @Test
    void loginUnsuccessful() {
        LoginBodyLombokModel data = new LoginBodyLombokModel();
        data.setEmail("peter@klaven");

        LoginResponseLombokModel response = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("/login")
                .then()
                .spec(responseSpec)
                .statusCode(400)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getError()).isEqualTo("Missing password");
    }
}
