package com.kazie.kazie.services.interfaces;

import com.kazie.kazie.models.dtos.requests.RealistionRequest;
import com.kazie.kazie.models.dtos.responses.RealisationResponse;
import com.kazie.kazie.models.entities.Realisation;

import java.util.List;

public interface RealisationServiceInterface {
    RealisationResponse ajouter(RealistionRequest realistionRequest);
    RealisationResponse editerParTitre(String titre, RealistionRequest realistionRequest);
    List<RealisationResponse> lister();
    void supprimerParTitre(String titre);
}
