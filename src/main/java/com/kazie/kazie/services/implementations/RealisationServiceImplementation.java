package com.kazie.kazie.services.implementations;

import com.kazie.kazie.exceptions.ResourceNotFoundException;
import com.kazie.kazie.models.dtos.requests.RealistionRequest;
import com.kazie.kazie.models.dtos.responses.RealisationResponse;
import com.kazie.kazie.models.entities.Professionnel;
import com.kazie.kazie.models.entities.Realisation;
import com.kazie.kazie.models.entities.Utilisateur;
import com.kazie.kazie.models.enums.UtilisateurRole;
import com.kazie.kazie.models.mappers.RealisationMapper;
import com.kazie.kazie.repositories.ProfessionnelRepository;
import com.kazie.kazie.repositories.RealisationRepository;
import com.kazie.kazie.repositories.UtilisateurRepository;
import com.kazie.kazie.services.interfaces.RealisationServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RealisationServiceImplementation implements RealisationServiceInterface {
    //injection de dépendance par constructeur
    private final RealisationRepository realisationRepository;
    private final RealisationMapper realisationMapper;
    private final UtilisateurRepository utilisateurRepository;
    private final ProfessionnelRepository professionnelRepository;

    @Override
    public RealisationResponse ajouter(RealistionRequest realistionRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Utilisateur utilisateur=utilisateurRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("Aucun utilisateur ne possède ce nom")
        );
        if (!utilisateur.getUtilisateurRole().equals(UtilisateurRole.PRO)){
            throw new ResourceNotFoundException("Action reservé au professionnel");
        }
        Professionnel professionnel=professionnelRepository.findByUtilisateur(utilisateur).orElseThrow(
                ()-> new ResourceNotFoundException("Aucune correspondace de compte")
        );
        Realisation realisation=realisationMapper.enEntite(realistionRequest,professionnel);
        realisationRepository.save(realisation);
        return realisationMapper.enDtos(realisation);
    }

    @Override
    public RealisationResponse editerParTitre(String titre, RealistionRequest realistionRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Utilisateur utilisateur=utilisateurRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("Aucun utilisateur ne possède ce nom")
        );
        if (!utilisateur.getUtilisateurRole().equals(UtilisateurRole.PRO)){
            throw new ResourceNotFoundException("Action reservé au professionnel");
        }
        Professionnel professionnel=professionnelRepository.findByUtilisateur(utilisateur).orElseThrow(
                ()-> new ResourceNotFoundException("Aucune correspondance de compte")
        );
        Realisation realisation=new Realisation();
        for (Realisation r: professionnel.getRealisations()){
            if(r.getTitre().equals(titre)){
                realisation=r;
            }
        }
        if (realisation==null){
            throw new ResourceNotFoundException("Aucune realisation avec ce titre");
        }
        realisation.setTitre(realistionRequest.getTitre());
        realisation.setUrlImage(realistionRequest.getUrlImage());
        realisation.setDescription(realistionRequest.getDescription());
        realisation.setDateModification(LocalDateTime.now());
        realisationRepository.save(realisation);
        return realisationMapper.enDtos(realisation);
    }

    @Override
    public List<RealisationResponse> lister() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Utilisateur utilisateur=utilisateurRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("Aucun utilisateur ne possède ce nom")
        );
        if (!utilisateur.getUtilisateurRole().equals(UtilisateurRole.PRO)){
            throw new ResourceNotFoundException("Action reservé au professionnel");
        }
        Professionnel professionnel=professionnelRepository.findByUtilisateur(utilisateur).orElseThrow(
                ()-> new ResourceNotFoundException("Aucune correspondance de compte")
        );
      List<RealisationResponse> realisationResponses=new ArrayList<>();
      for (Realisation realisation: professionnel.getRealisations()){
          realisationResponses.add( realisationMapper.enDtos(realisation));
      }
      return realisationResponses;
    }

    @Override
    public void supprimerParTitre(String titre) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Utilisateur utilisateur=utilisateurRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("Aucun utilisateur ne possède ce nom")
        );
        if (!utilisateur.getUtilisateurRole().equals(UtilisateurRole.PRO)){
            throw new ResourceNotFoundException("Action reservé au professionnel");
        }
        Professionnel professionnel=professionnelRepository.findByUtilisateur(utilisateur).orElseThrow(
                ()-> new ResourceNotFoundException("Aucune correspondance de compte")
        );
        Realisation realisation=new Realisation();
        for (Realisation r: professionnel.getRealisations()){
            if(r.getTitre().equals(titre)){
                realisation=r;
            }
        }
        if (realisation==null){
            throw new ResourceNotFoundException("Aucune realisation avec ce titre");
        }
        realisationRepository.delete(realisation);
    }
}
