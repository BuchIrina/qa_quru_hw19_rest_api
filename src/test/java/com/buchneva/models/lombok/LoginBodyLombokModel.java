package com.buchneva.models.lombok;

import lombok.Data;

@Data
public class LoginBodyLombokModel {
    //String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";
    private String email, password;
}
