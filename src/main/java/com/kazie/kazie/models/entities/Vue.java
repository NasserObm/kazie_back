package com.kazie.kazie.models.entities;

import com.kazie.kazie.models.enums.TypeVue;
import jakarta.persistence.*;

@Entity
public class Vue {
    //variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeVue type;
    private String cibleNom;

    //Relation avec les autres objets
    @ManyToOne
    private Utilisateur utilisateur;

    //constructeur
    public Vue() {
    }

    //getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public TypeVue getType() {
        return type;
    }

    public void setType(TypeVue type) {
        this.type = type;
    }

    public String getCibleNom() {
        return cibleNom;
    }

    public void setCibleNom(String cibleNom) {
        this.cibleNom = cibleNom;
    }
}
