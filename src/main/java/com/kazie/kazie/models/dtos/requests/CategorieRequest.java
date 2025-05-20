package com.kazie.kazie.models.dtos.requests;

import lombok.Builder;


public class CategorieRequest {
    //Variable de création
    private String nom;
    private String description;
    private String urlIcone;

    //Constructeur

    public CategorieRequest() {
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

    public String getUrlIcone() {
        return urlIcone;
    }

    public void setUrlIcone(String urlIcone) {
        this.urlIcone = urlIcone;
    }

    //fonctions ou methode statique
}
