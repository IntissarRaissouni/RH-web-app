package com.IT.SpringBootAngular.dto;

import java.util.List;

public class LoginResponse {
    private String jwToken;
    private List<String> roles;



    public LoginResponse(String jwToken, List<String> roles) {
        this.jwToken = jwToken;
        this.roles=roles;
    }

    public String getJwToken() {
        return jwToken;
    }

    public void setJwToken(String jwToken) {
        this.jwToken = jwToken;
    }
}
