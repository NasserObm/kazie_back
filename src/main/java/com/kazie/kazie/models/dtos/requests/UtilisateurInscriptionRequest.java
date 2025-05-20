package com.kazie.kazie.models.dtos.requests;

import jakarta.persistence.Column;

public class UtilisateurInscriptionRequest {
    //Variable d'inscription
    private String email;
    private String nom;
    private String prenom;
    private String numero;
    private String urlProfile;
    private String motDePasse;
    //constructeur
    public UtilisateurInscriptionRequest() {
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
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }


    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
