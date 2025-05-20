package com.kazie.kazie.models.dtos.responses;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class RealisationResponse {
    //retour pour la vue du client
    private LocalDateTime dateCreation;
    private String titre;
    private String description;
    private String urlImage;

    //constructeur
    public RealisationResponse() {
    }

    //Getters et Setters

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

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
