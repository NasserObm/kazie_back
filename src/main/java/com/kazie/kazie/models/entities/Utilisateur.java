package com.kazie.kazie.models.entities;

import com.kazie.kazie.models.enums.UtilisateurRole;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur implements UserDetails {
    //variable des utilisateurs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String nom;
    @Column
    private String prenom;
    @Column(nullable = false)
    private String numero;
    @Column
    private String urlProfile;
    @Column
    private String codeOtp;
    @Column
    private LocalDateTime otpExpiration;
    @Column(nullable = false)
    private boolean compteValide;
    @Column(nullable = false)
    private String motDePasse;
    @Enumerated(EnumType.STRING)
    private UtilisateurRole utilisateurRole;

    //constructeur
    public Utilisateur() {
    }

    //Getters et Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    public boolean isCompteValide() {
        return compteValide;
    }

    public void setCompteValide(boolean compteValide) {
        this.compteValide = compteValide;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public UtilisateurRole getUtilisateurRole() {
        return utilisateurRole;
    }

    public void setUtilisateurRole(UtilisateurRole utilisateurRole) {
        this.utilisateurRole = utilisateurRole;
    }

    //methode de l'interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
    //fonction ou methode statique
}