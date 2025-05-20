package com.kazie.kazie.models.dtos.requests;

import jakarta.persistence.Column;

public class ProInscriptionRequest {
    //Variable d'inscription
    private String email;
    private String nom;
    private String prenom;
    private String numero;
    private String urlProfile;
    private String motDePasse;
    private String adresse;
    private String identite;
    private String nomMetier;

    //constructeur
    public ProInscriptionRequest() {
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getIdentite() {
        return identite;
    }

    public void setIdentite(String identite) {
        this.identite = identite;
    }


    public void setNomMetier(String nomMetier) {
        this.nomMetier = nomMetier;
    }

    public String getNomMetier() {
        return nomMetier;
    }
}
