package com.kazie.kazie.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "metiers")
public class Metier {
    //Variables

    //Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //variables de la classe
    @Column(nullable = false,unique = true)
    private String nom;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String urlImage;
    @Column(nullable = false)
    private int nombreVue;
    @Column(nullable = false)
    private boolean populaire;
    //relation avec les autres objets
    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private Categorie categorie;
    @OneToMany(mappedBy = "metier")
    private List<Professionnel> professionnels;
    //constructeur
    public Metier() {
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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Professionnel> getProfessionnels() {
        return professionnels;
    }

    public void setProfessionnels(List<Professionnel> professionnels) {
        this.professionnels = professionnels;
    }

    //Autres methode ou fonction statique
    public void ajouterVue(){
        this.nombreVue++;
    }
}
