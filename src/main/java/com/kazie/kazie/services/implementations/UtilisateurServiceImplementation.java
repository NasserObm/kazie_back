package com.kazie.kazie.services.implementations;

import com.kazie.kazie.models.dtos.requests.ProfileUtilisateurRequest;
import com.kazie.kazie.exceptions.ResourceNotFoundException;
import com.kazie.kazie.models.dtos.requests.DevenirProRequest;
import com.kazie.kazie.models.dtos.responses.UtilisateurResponse;
import com.kazie.kazie.models.entities.Metier;
import com.kazie.kazie.models.entities.Professionnel;
import com.kazie.kazie.models.entities.Utilisateur;
import com.kazie.kazie.models.enums.TypeVue;
import com.kazie.kazie.models.enums.UtilisateurRole;
import com.kazie.kazie.repositories.MetierRepository;
import com.kazie.kazie.repositories.ProfessionnelRepository;
import com.kazie.kazie.repositories.UtilisateurRepository;
import com.kazie.kazie.services.interfaces.UtilisateurServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service

public class UtilisateurServiceImplementation implements UtilisateurServiceInterface {
    private final UtilisateurRepository utilisateurRepository;
    private final MetierRepository metierRepository;

    public UtilisateurServiceImplementation(UtilisateurRepository utilisateurRepository, MetierRepository metierRepository, ProfessionnelRepository professionnelRepository, VueServiceImpl vueService) {
        this.utilisateurRepository = utilisateurRepository;
        this.metierRepository = metierRepository;
        this.professionnelRepository = professionnelRepository;
        this.vueService = vueService;
    }

    private final ProfessionnelRepository professionnelRepository;
    private final VueServiceImpl vueService;


    @Override
    public void editerCompteSimple(ProfileUtilisateurRequest utilisateurInscriptionRequest) {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Utilisateur utilisateur=utilisateurRepository.findByEmail(email).orElseThrow(
                    ()-> new ResourceNotFoundException("Aucun utilisateur ne possède ce nom")
            );
            utilisateur.setNom(utilisateurInscriptionRequest.getNom());
            utilisateur.setPrenom(utilisateurInscriptionRequest.getPrenom());
            utilisateur.setNumero(utilisateurInscriptionRequest.getNumero());
            utilisateur.setUrlProfile(utilisateurInscriptionRequest.getUrlProfile());
            utilisateurRepository.save(utilisateur);
        }catch (Exception e){
            throw new ResourceNotFoundException("Problème lors de l'edit de l'utilisateur");
        }
    }

    @Override
    public UtilisateurResponse voirProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Utilisateur utilisateur=utilisateurRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("Aucun utilisateur ne possède ce nom")
        );
        UtilisateurResponse utilisateurResponse=new UtilisateurResponse();
        utilisateurResponse.setNom(utilisateur.getNom());
        utilisateurResponse.setPrenom(utilisateur.getPrenom());
        utilisateurResponse.setNumero(utilisateur.getNumero());
        utilisateurResponse.setEmail(utilisateur.getEmail());
        utilisateurResponse.setUrlProfile(utilisateur.getUrlProfile());
        return utilisateurResponse;
    }

    @Override
    public void changerMotDePasse() {
        //Todo check par email pour la modification du mot de passe
    }

    @Override
    public void devenirPro(DevenirProRequest devenirProRequest){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Utilisateur utilisateur=utilisateurRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("Aucun utilisateur ne possède ce nom")
        );
        if (!utilisateur.getUtilisateurRole().equals(UtilisateurRole.CLIENT)){
            throw new ResourceNotFoundException("Action reservé au client");
        }
        utilisateur.setUtilisateurRole(UtilisateurRole.PRO);
        Professionnel professionnel=new Professionnel();
        professionnel.setUtilisateur(utilisateur);
        professionnel.setNote(0);
        professionnel.setNombreVue(0);
        professionnel.setIdentite(devenirProRequest.getIdentite());
        professionnel.setAdresse(devenirProRequest.getAdresse());
        Metier metier=metierRepository.findByNom(devenirProRequest.getNomMetier()).orElseThrow(
                ()-> new ResourceNotFoundException("Aucun metier ne  possède ce nom")
        );
        professionnel.setMetier(metier);
        professionnelRepository.save(professionnel);
    }

    @Override
    public void changerMetier(String nomMetier) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Utilisateur utilisateur=utilisateurRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("Aucun utilisateur ne possède ce nom")
        );
        if (!utilisateur.getUtilisateurRole().equals(UtilisateurRole.PRO)){
            throw new ResourceNotFoundException("Action rservé au pro");
        }
        Professionnel professionnel=professionnelRepository.findByUtilisateur(utilisateur).orElseThrow(
                ()-> new ResourceNotFoundException("Aucune Correspondance pro")
        );
        Metier metier=metierRepository.findByNom(nomMetier).orElseThrow(
                ()-> new ResourceNotFoundException("Aucun metier ne possède ce nom")
        );
        professionnel.setMetier(metier);
        professionnelRepository.save(professionnel);
    }

    @Override
    public void bloquerCompte(String email) {
        Utilisateur utilisateur=utilisateurRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("Aucun utilisateur ne possède cet email")
        );
        if (utilisateur.getUtilisateurRole().equals(UtilisateurRole.ADMIN)){
            throw new ResourceNotFoundException("Impossible de faire cette action sur ce compte");
        }
        utilisateur.setCompteValide(false);
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public void debloquerCompte(String email) {
        Utilisateur utilisateur=utilisateurRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("Aucun utilisateur ne possède cet email")
        );
        if (utilisateur.getUtilisateurRole().equals(UtilisateurRole.ADMIN)){
            throw new ResourceNotFoundException("Impossible de faire cette action sur ce compte");
        }
        utilisateur.setCompteValide(true);
        utilisateurRepository.save(utilisateur);

    }

    @Override
    public void voir(String email) {
        String emailClient = SecurityContextHolder.getContext().getAuthentication().getName();
        Utilisateur client=utilisateurRepository.findByEmail(emailClient).orElseThrow(
                ()-> new ResourceNotFoundException("Aucun utilisateur ne possède ce nom")
        );
        if (!client.getUtilisateurRole().equals(UtilisateurRole.CLIENT)){
            throw new ResourceNotFoundException("Action rservé au client");
        }
        Utilisateur utilisateur=utilisateurRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("Aucun utilisateur ne possède cet email")
        );
        if (utilisateur.getUtilisateurRole().equals(UtilisateurRole.PRO)){

        }
        boolean nouvelleVue = vueService.ajouterVueSiNouvelle(TypeVue.CATEGORIE, email);
        if (nouvelleVue){
            Professionnel professionnel=professionnelRepository.findByUtilisateur(utilisateur).orElseThrow(
                    ()->new ResourceNotFoundException("Aucune Correspondance avec ce compte pro")
            );
            professionnel.ajouterVue();
            professionnelRepository.save(professionnel);
        }

    }
}
