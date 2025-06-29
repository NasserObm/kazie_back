package com.kazie.kazie.models.mappers;

import com.kazie.kazie.models.dtos.requests.ProInscriptionRequest;
import com.kazie.kazie.models.dtos.requests.UtilisateurInscriptionRequest;
import com.kazie.kazie.models.entities.Utilisateur;
import com.kazie.kazie.models.enums.UtilisateurRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component

public class AuthenticationMapper {
    public AuthenticationMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //injection de dépandance par constructeur
    private final PasswordEncoder passwordEncoder;

    public Utilisateur enEntiteClient(UtilisateurInscriptionRequest utilisateurInscriptionRequest){
        Utilisateur utilisateur=new Utilisateur();
        utilisateur.setEmail(utilisateurInscriptionRequest.getEmail());
        utilisateur.setNom(utilisateurInscriptionRequest.getNom());
        utilisateur.setPrenom(utilisateurInscriptionRequest.getPrenom());
        utilisateur.setNumero(utilisateurInscriptionRequest.getNumero());
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurInscriptionRequest.getMotDePasse()));
        utilisateur.setUrlProfile(utilisateurInscriptionRequest.getUrlProfile());
        utilisateur.setUtilisateurRole(UtilisateurRole.CLIENT);
        utilisateur.setCompteValide(true);

        return utilisateur;
    }
    public Utilisateur enEntiteAdmin(UtilisateurInscriptionRequest utilisateurInscriptionRequest){
        Utilisateur utilisateur=new Utilisateur();
        utilisateur.setEmail(utilisateurInscriptionRequest.getEmail());
        utilisateur.setNom(utilisateurInscriptionRequest.getNom());
        utilisateur.setPrenom(utilisateurInscriptionRequest.getPrenom());
        utilisateur.setNumero(utilisateurInscriptionRequest.getNumero());
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurInscriptionRequest.getMotDePasse()));
        utilisateur.setUrlProfile(utilisateurInscriptionRequest.getUrlProfile());
        utilisateur.setCompteValide(true);
        utilisateur.setUtilisateurRole(UtilisateurRole.ADMIN);
        return utilisateur;
    }
    public Utilisateur enEntitePro(ProInscriptionRequest proInscriptionRequest){
        Utilisateur utilisateur=new Utilisateur();
        utilisateur.setEmail(proInscriptionRequest.getEmail());
        utilisateur.setNom(proInscriptionRequest.getNom());
        utilisateur.setPrenom(proInscriptionRequest.getPrenom());
        utilisateur.setNumero(proInscriptionRequest.getNumero());
        utilisateur.setMotDePasse(passwordEncoder.encode(proInscriptionRequest.getMotDePasse()));
        utilisateur.setUrlProfile(proInscriptionRequest.getUrlProfile());
        utilisateur.setCompteValide(true);
        utilisateur.setUtilisateurRole(UtilisateurRole.PRO);
        return utilisateur;
    }
}
