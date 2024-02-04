package com.example.bakalaurinis.security.dtos;

public class LoginResponse {

    //mano tokenas kuri siusiu
    private final String jwt;

    public LoginResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
