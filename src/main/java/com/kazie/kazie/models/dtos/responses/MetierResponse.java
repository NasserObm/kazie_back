package com.kazie.kazie.models.dtos.responses;

import java.util.List;

public class MetierResponse {
    //variable du retour
    private String nom;
    private String description;
    private String urlImage;
    private String nomCategorie;
    private int nombreVue;
    private boolean populaire;
    private List<ProfesionnelResponse> profesionnelResponses;

    //constructeur
    public MetierResponse() {
    }
    //Getters et Setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public int getNombreVue() {
        return nombreVue;
    }

    public void setNombreVue(int nombreVue) {
        this.nombreVue = nombreVue;
    }

    public boolean isPopulaire() {
        return populaire;
    }

    public void setPopulaire(boolean populaire) {
        this.populaire = populaire;
    }

    public List<ProfesionnelResponse> getProfesionnelResponses() {
        return profesionnelResponses;
    }

    public void setProfesionnelResponses(List<ProfesionnelResponse> profesionnelResponses) {
        this.profesionnelResponses = profesionnelResponses;
    }
    //methode ou fonction statique
}
