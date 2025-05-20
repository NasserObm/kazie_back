package com.kazie.kazie.models.dtos.requests;

public class MetierRequest {
    //variables de création
    private String nom;
    private String description;
    private String urlImage;
    private String nomCategories;

    //Constructeur
    public MetierRequest() {
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

    public String getNomCategories() {
        return nomCategories;
    }

    public void setNomCategories(String nomCategories) {
        this.nomCategories = nomCategories;
    }
    //methode ou fonction statique
}
