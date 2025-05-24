package com.kazie.kazie.models.dtos.responses;

public class UtilisateurResponse {
    //variable de présenatation de compte
    private String email;
    private String nom;
    private String prenom;
    private String numero;
    private String UrlProfile;
    //constructeur
    public UtilisateurResponse() {
    }
    //Getters et Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getUrlProfile() {
        return UrlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        UrlProfile = urlProfile;
    }
}
