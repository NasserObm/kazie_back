package com.kazie.kazie.models.mappers;

import com.kazie.kazie.models.dtos.requests.ProInscriptionRequest;
import com.kazie.kazie.models.entities.Metier;
import com.kazie.kazie.models.entities.Professionnel;
import com.kazie.kazie.models.entities.Realisation;
import com.kazie.kazie.models.entities.Utilisateur;
import com.kazie.kazie.repositories.RealisationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProMapper {
    //injection de dépendance par constructeur
    private final RealisationRepository realisationRepositor;

    public Professionnel enEntite(Utilisateur utilisateur, ProInscriptionRequest proInscriptionRequest, Metier metier){
        Professionnel pro=new Professionnel();
        pro.setAdresse(proInscriptionRequest.getAdresse());
        pro.setIdentite(proInscriptionRequest.getIdentite());
        pro.setNote(0);
        pro.setNombreVue(0);
        pro.setUtilisateur(utilisateur);
        pro.setPopulaire(false);
        pro.setMetier(metier);
        //chargement des realisations du pro
        List<Realisation> realisations=new ArrayList<>();
        for (Realisation r:realisationRepositor.findAll()){
            if (r.getProfessionnel().getUtilisateur().getEmail().equals(utilisateur.getEmail())){
                realisations.add(r);
            }
        }
        pro.setRealisations(realisations);
        return pro;
    }
}
