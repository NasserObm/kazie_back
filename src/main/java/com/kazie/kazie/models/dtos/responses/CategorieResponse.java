package com.kazie.kazie.models.dtos.responses;

import java.util.List;

public class CategorieResponse {
    //Variables de retour
    private String nom;
    private String description;
    private String urlIcone;
    private int nombreVue;
    private boolean populaire;
    private List<MetierResponse> metierResponses;

    //Constructeurs

    public CategorieResponse() {
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

    public List<MetierResponse> getMetierResponses() {
        return metierResponses;
    }

    public void setMetierResponses(List<MetierResponse> metierResponses) {
        this.metierResponses = metierResponses;
    }
    //methodes ou fonctions statiques
}
