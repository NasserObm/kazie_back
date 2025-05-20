package com.kazie.kazie.models.mappers;

import com.kazie.kazie.models.dtos.requests.MetierRequest;
import com.kazie.kazie.models.dtos.responses.MetierResponse;
import com.kazie.kazie.models.dtos.responses.ProfesionnelResponse;
import com.kazie.kazie.models.dtos.responses.RealisationResponse;
import com.kazie.kazie.models.entities.*;
import com.kazie.kazie.repositories.ProfessionnelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MetierMapper {
    //injection de dépendance par constructeur
    private final ProfessionnelRepository professionnelRepository;
    private final RealisationMapper realisationMapper;


    public Metier enEntite(MetierRequest metierRequest, Categorie categorie){
        Metier metier=new Metier();
        metier.setNom(metierRequest.getNom());
        metier.setDescription(metierRequest.getDescription());
        metier.setUrlImage(metierRequest.getUrlImage());
        metier.setPopulaire(false);
        metier.setNombreVue(0);
        metier.setCategorie(categorie);
        List<Professionnel> professionnels=new ArrayList<>();
        for (Professionnel professionnel: professionnelRepository.findAll()){
            if(professionnel.getMetier().getNom().equals(metierRequest.getNom())){
                professionnels.add(professionnel);
            }
        }
        metier.setProfessionnels(professionnels);


        return metier;
    }
    public MetierResponse enDtos(Metier metier,String nomCategorie){
        MetierResponse metierResponse=new MetierResponse();
        metierResponse.setNom(metier.getNom());
        metierResponse.setDescription(metier.getDescription());
        metierResponse.setUrlImage(metier.getUrlImage());
        metierResponse.setNombreVue(metier.getNombreVue());
        metierResponse.setPopulaire(metier.isPopulaire());
        metierResponse.setNomCategorie(nomCategorie);

        //remplissage pro
        List<Utilisateur> utilisateurs=new ArrayList<>();
        List<ProfesionnelResponse> profesionnelResponses=new ArrayList<>();
        for (Professionnel professionnel: professionnelRepository.findAll()){
     if (professionnel.getMetier().getNom().equals(metier.getNom())){
         ProfesionnelResponse profesionnelResponse=new ProfesionnelResponse();
         profesionnelResponse.setNom(professionnel.getUtilisateur().getNom());
         profesionnelResponse.setPrenom(professionnel.getUtilisateur().getPrenom());
         profesionnelResponse.setAdresse(professionnel.getAdresse());
         profesionnelResponse.setEmail(professionnel.getUtilisateur().getEmail());
         profesionnelResponse.setNumero(professionnel.getUtilisateur().getNumero());
         profesionnelResponse.setNomMetier(professionnel.getMetier().getNom());
         profesionnelResponse.setUrlProfile(professionnel.getUtilisateur().getUrlProfile());
         profesionnelResponse.setNombreVue(professionnel.getNombreVue());
         profesionnelResponse.setNote(professionnel.getNote());
         List<RealisationResponse> realisations=new ArrayList<>();
         for (Realisation r:professionnel.getRealisations()){
             realisations.add(realisationMapper.enDtos(r));
         }
         profesionnelResponse.setRealisationResponses(realisations);
         profesionnelResponses.add(profesionnelResponse);

     }
        }
        metierResponse.setProfesionnelResponses(profesionnelResponses);

        return metierResponse;
    }
}
