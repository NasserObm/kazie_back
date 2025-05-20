package com.kazie.kazie.models.dtos.responses;

public class ConnexionReponse {
    //variable
    private String token;

    //constructeurs
    public ConnexionReponse(String token) {
        this.token = token;
    }

    public ConnexionReponse() {
    }

    //Getters et Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
