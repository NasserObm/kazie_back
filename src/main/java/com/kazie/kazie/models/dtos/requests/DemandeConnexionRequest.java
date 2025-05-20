package com.kazie.kazie.models.dtos.requests;

public class DemandeConnexionRequest {
    //variable de connexion
    private String email;
    private String motDePasse;

    //constructeur
    public DemandeConnexionRequest() {
    }

    //Getters et Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
