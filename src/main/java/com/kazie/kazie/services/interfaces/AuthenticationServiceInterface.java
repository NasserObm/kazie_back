package com.kazie.kazie.services.interfaces;

import com.kazie.kazie.models.dtos.requests.DemandeConnexionRequest;
import com.kazie.kazie.models.dtos.requests.ProInscriptionRequest;
import com.kazie.kazie.models.dtos.requests.UtilisateurInscriptionRequest;
import com.kazie.kazie.models.dtos.responses.ConnexionReponse;

public interface AuthenticationServiceInterface {
    ConnexionReponse connecter(DemandeConnexionRequest demandeConnexionRequest);
    ConnexionReponse inscriptionPro(ProInscriptionRequest proInscriptionRequest);
    ConnexionReponse inscriptionClient(UtilisateurInscriptionRequest utilisateurInscriptionRequest);
    ConnexionReponse inscriptionAdmin(UtilisateurInscriptionRequest utilisateurInscriptionRequest);
}
