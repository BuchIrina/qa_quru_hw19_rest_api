package com.buchneva.tests;

import com.buchneva.models.lombok.LoginBodyLombokModel;
import com.buchneva.models.lombok.LoginResponseLombokModel;
import com.buchneva.models.pojo.LoginBodyModel;
import com.buchneva.models.pojo.LoginResponseModel;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Test;

import static com.buchneva.helper.CustomApiListener.withCustomTemplates;
import static com.buchneva.specs.LoginSpecs.loginRequestSpec;
import static com.buchneva.specs.LoginSpecs.loginResponseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

public class ReqresInExtendedTests {

    @Test
    void loginTest() {
        // BAD PRACTICE, move body to model
        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
        // { "token": "QpwL5tke4Pnpja7X4" }
    }

    @Test
    void loginWithPojoModelTest() {
        LoginBodyModel body = new LoginBodyModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        LoginResponseModel response = given()
                .log().uri()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
        // { "token": "QpwL5tke4Pnpja7X4" }

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithLombokModelTest() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .log().uri()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);
        // { "token": "QpwL5tke4Pnpja7X4" }

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithAllureTest() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .filter(new AllureRestAssured())
                .log().uri()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);
        // { "token": "QpwL5tke4Pnpja7X4" }

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithCustomAllureTest() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .filter(withCustomTemplates())
                .log().uri()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);
        // { "token": "QpwL5tke4Pnpja7X4" }

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithSpecTest() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .spec(loginRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(loginResponseSpec)
                .extract().as(LoginResponseLombokModel.class);
        // { "token": "QpwL5tke4Pnpja7X4" }

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }
}
