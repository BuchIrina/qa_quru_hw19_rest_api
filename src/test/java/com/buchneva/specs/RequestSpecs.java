package com.buchneva.specs;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static com.buchneva.helper.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;



public class RequestSpecs {

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in/api")
            .log().uri()
            .contentType(JSON);

}
