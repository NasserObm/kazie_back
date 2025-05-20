package com.kazie.kazie.models.mappers;

import com.kazie.kazie.models.dtos.requests.RealistionRequest;
import com.kazie.kazie.models.dtos.responses.RealisationResponse;
import com.kazie.kazie.models.entities.Professionnel;
import com.kazie.kazie.models.entities.Realisation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RealisationMapper {

    public Realisation enEntite(RealistionRequest realistionRequest, Professionnel professionnel){
        Realisation realisation=new Realisation();
        realisation.setDateCreation(LocalDateTime.now());
        realisation.setTitre(realistionRequest.getTitre());
        realisation.setDescription(realistionRequest.getDescription());
        realisation.setUrlImage(realistionRequest.getUrlImage());
        realisation.setProfessionnel(professionnel);

        return realisation;
    }
    public RealisationResponse enDtos(Realisation realisation){
        RealisationResponse realisationResponse=new RealisationResponse();
        realisationResponse.setTitre(realisation.getTitre());
        realisationResponse.setDescription(realisation.getDescription());
        realisationResponse.setUrlImage(realisation.getUrlImage());
        realisationResponse.setDateCreation(realisation.getDateCreation());

        return realisationResponse;
    }
}
