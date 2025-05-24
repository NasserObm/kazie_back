package com.kazie.kazie.models.dtos.responses;

import java.util.List;

public class ProfesionnelResponse {
    //variable de vue du client
    private String email;
    private String nom;
    private String prenom;
    private String numero;
    private String urlProfile;
    private String adresse;
    private String nomMetier;
    private int nombreVue;
    private int note;
    private List<RealisationResponse> realisationResponses;

    //constructeur
    public ProfesionnelResponse() {
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNomMetier() {
        return nomMetier;
    }

    public void setNomMetier(String nomMetier) {
        this.nomMetier = nomMetier;
    }

    public int getNombreVue() {
        return nombreVue;
    }

    public void setNombreVue(int nombreVue) {
        this.nombreVue = nombreVue;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public List<RealisationResponse> getRealisationResponses() {
        return realisationResponses;
    }

    public void setRealisationResponses(List<RealisationResponse> realisationResponses) {
        this.realisationResponses = realisationResponses;
    }
}
