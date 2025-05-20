package com.kazie.kazie.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "professionnels")
public class Professionnel {
    //variable du pro
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String adresse;
    @Column(nullable = false)
    private String identite;
    @Column(nullable = false)
    private int nombreVue;
    @Column(nullable = false)
    private int note;
    @Column
    private boolean populaire;


    //relation avec les autres objets
    @ManyToOne
    @JoinColumn(name = "metier_id",nullable = false)
    private Metier metier;

    @OneToOne
    @JoinColumn(name ="utilisateur_id")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "professionnel")
    private List<Realisation> realisations;


    //constructeur
    public Professionnel() {
    }

    //Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Metier getMetier() {
        return metier;
    }

    public void setMetier(Metier metier) {
        this.metier = metier;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Realisation> getRealisations() {
        return realisations;
    }

    public void setRealisations(List<Realisation> realisations) {
        this.realisations = realisations;
    }

    public boolean isPopulaire() {
        return populaire;
    }

    public void setPopulaire(boolean populaire) {
        this.populaire = populaire;
    }

    //methodes ou fonctions statique
    public void ajouterVue(){
        this.nombreVue++;
    }
}

