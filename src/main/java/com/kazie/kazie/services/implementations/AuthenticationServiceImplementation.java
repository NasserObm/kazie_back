package com.kazie.kazie.services.implementations;

import com.kazie.kazie.exceptions.AuthenticationException;
import com.kazie.kazie.exceptions.ResourceNotFoundException;
import com.kazie.kazie.models.dtos.requests.DemandeConnexionRequest;
import com.kazie.kazie.models.dtos.requests.ProInscriptionRequest;
import com.kazie.kazie.models.dtos.requests.UtilisateurInscriptionRequest;
import com.kazie.kazie.models.dtos.responses.ConnexionReponse;
import com.kazie.kazie.models.entities.Metier;
import com.kazie.kazie.models.entities.Professionnel;
import com.kazie.kazie.models.entities.Utilisateur;
import com.kazie.kazie.models.mappers.AuthenticationMapper;
import com.kazie.kazie.models.mappers.ProMapper;
import com.kazie.kazie.repositories.MetierRepository;
import com.kazie.kazie.repositories.ProfessionnelRepository;
import com.kazie.kazie.repositories.UtilisateurRepository;
import com.kazie.kazie.securities.JwtService;
import com.kazie.kazie.services.interfaces.AuthenticationServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImplementation implements AuthenticationServiceInterface {
    //injection de dépendance par constructeur
    private final UtilisateurRepository utilisateurRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthenticationMapper authenticationMapper;
    private final ProMapper proMapper;
    private final ProfessionnelRepository professionnelRepository;
    private final MetierRepository metierRepository;

    @Override
    public ConnexionReponse connecter(DemandeConnexionRequest demandeConnexionRequest) {
      try {
          authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(
                          demandeConnexionRequest.getEmail(),
                          demandeConnexionRequest.getMotDePasse()
                  )
          );
      }catch (AuthenticationException e){
          throw  new ResourceNotFoundException("Identifiant invalides");
      }
        Utilisateur utilisateur=utilisateurRepository.findByEmail(demandeConnexionRequest.getEmail()).orElseThrow(
                ()-> new ResourceNotFoundException("Aucun utilisateur ne possède cet email")
        );
      return new ConnexionReponse(jwtService.generateToken(utilisateur));
    }

    @Override
    public ConnexionReponse inscriptionPro(ProInscriptionRequest proInscriptionRequest) {
        try{
            if(utilisateurRepository.existsByEmail(proInscriptionRequest.getEmail())){
                throw new ResourceNotFoundException("Un utilisateur possède déjà cet email");
            }
            Utilisateur utilisateur=authenticationMapper.enEntitePro(proInscriptionRequest);
            utilisateurRepository.save(utilisateur);
            //Inscription pro
            try {
                Metier metier=metierRepository.findByNom(proInscriptionRequest.getNomMetier()).orElseThrow(
                        ()-> new ResourceNotFoundException("Aucun metier ne possède ce nom")
                );
                Professionnel professionnel=proMapper.enEntite(utilisateur,proInscriptionRequest,metier);
                professionnelRepository.save(professionnel);
            }catch (Exception e){
                utilisateurRepository.delete(utilisateur);//RollBack en cas d'erreur
                throw new ResourceNotFoundException("Erreur dans la création du professionnel:"+ e.getMessage());
            }
            return new ConnexionReponse(jwtService.generateToken(utilisateur));
        }catch (Exception e){
            throw new ResourceNotFoundException("Erreur lors de l'inscription du professionnel:"+e.getMessage());
        }

    }

    @Override
    public ConnexionReponse inscriptionClient(UtilisateurInscriptionRequest utilisateurInscriptionRequest) {
        try{
            if (utilisateurRepository.existsByEmail(utilisateurInscriptionRequest.getEmail())){
                throw new ResourceNotFoundException("Un utilisateur possède déjà cet email");
            }
            Utilisateur utilisateur=authenticationMapper.enEntiteClient(utilisateurInscriptionRequest);
            utilisateurRepository.save(utilisateur);
            return new ConnexionReponse(jwtService.generateToken(utilisateur));

        }catch (Exception e){
           throw new ResourceNotFoundException("Erreur lors de l'inscription du client: "+e.getMessage());
        }
    }

    @Override
    public ConnexionReponse inscriptionAdmin(UtilisateurInscriptionRequest utilisateurInscriptionRequest) {
        try{
            if (utilisateurRepository.existsByEmail(utilisateurInscriptionRequest.getEmail())){
                throw new ResourceNotFoundException("Un utilisateur possède déjà cet email");
            }
            Utilisateur utilisateur=authenticationMapper.enEntiteAdmin(utilisateurInscriptionRequest);
            utilisateurRepository.save(utilisateur);
            return new ConnexionReponse(jwtService.generateToken(utilisateur));

        }catch (Exception e){
            throw new ResourceNotFoundException("Erreur lors de l'inscription de l'admin: "+e.getMessage());
        }
    }
}
