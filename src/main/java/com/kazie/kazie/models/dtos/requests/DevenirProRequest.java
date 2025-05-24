package com.kazie.kazie.models.dtos.requests;

public class DevenirProRequest {
    private String nomMetier;
    private String adresse;
    private String identite;

    public DevenirProRequest() {
    }

    public DevenirProRequest(String nomMetier, String adresse, String identite) {
        this.nomMetier = nomMetier;
        this.adresse = adresse;
        this.identite = identite;
    }

    public String getNomMetier() {
        return nomMetier;
    }

    public void setNomMetier(String nomMetier) {
        this.nomMetier = nomMetier;
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
}
