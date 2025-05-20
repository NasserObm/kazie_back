package com.kazie.kazie.models.dtos.requests;

public class RealistionRequest {
    //variable de créaion d'une realisation
    private String titre;
    private String description;
    private String urlImage;

    //constructeurs
    public RealistionRequest(String titre, String description, String urlImage) {
        this.titre = titre;
        this.description = description;
        this.urlImage = urlImage;
    }
    public RealistionRequest() {
    }

    //Getters et Setters
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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
}
