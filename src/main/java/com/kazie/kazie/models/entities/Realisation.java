package com.kazie.kazie.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "realisations")
public class Realisation {

    //variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    @Column(nullable = false)
    private String titre;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String urlImage;
    @ManyToOne
    @JoinColumn(name = "professionnel_id",nullable = false,unique = true)
    private Professionnel professionnel;

    //Constructeurs
    public Realisation(Long id, LocalDateTime dateCreation, LocalDateTime dateModification, String titre, String description, String urlImage, Professionnel professionnel) {
        this.id = id;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.titre = titre;
        this.description = description;
        this.urlImage = urlImage;
        this.professionnel = professionnel;
    }
    public Realisation() {
    }

    //Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
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

    public Professionnel getProfessionnel() {
        return professionnel;
    }

    public void setProfessionnel(Professionnel professionnel) {
        this.professionnel = professionnel;
    }
    //methode ou fonction statique
}
