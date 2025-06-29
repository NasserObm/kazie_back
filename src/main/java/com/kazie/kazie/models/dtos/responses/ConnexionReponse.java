package com.kazie.kazie.models.dtos.responses;

public class ConnexionReponse {
    private String token;
    private String nom;
    private String email;
    private String urlProfile;

    public ConnexionReponse() {}

    public ConnexionReponse(String token, String nom, String email, String urlProfile) {
        this.token = token;
        this.nom = nom;
        this.email = email;
        this.urlProfile = urlProfile;
    }

    // Getters & Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }
}
