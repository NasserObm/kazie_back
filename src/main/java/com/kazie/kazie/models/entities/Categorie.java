package com.kazie.kazie.models.entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name = "categories")
public class Categorie {

    //Toutes les variables

    //Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //variables metiers
    @Column(nullable = false,unique = true)
    private String nom;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String urlIcone;
    @Column(nullable = false)
    private int nombreVue;
    @Column(nullable = false)
    private boolean populaire;

    //relation avec les autres objets
    @OneToMany(mappedBy = "categorie")
    private List<Metier> metiers;
    //Constructeur

    public Categorie() {
    }
    //Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Metier> getMetiers() {
        return metiers;
    }

    public void setMetiers(List<Metier> metiers) {
        this.metiers = metiers;
    }
    //fonctions ou methodes statiques
    public void ajouterVue(){
        this.nombreVue++;
    }
}
