package com.kazie.kazie.services.interfaces;

import com.kazie.kazie.models.dtos.requests.ProfileUtilisateurRequest;
import com.kazie.kazie.models.dtos.requests.DevenirProRequest;
import com.kazie.kazie.models.dtos.responses.UtilisateurResponse;

public interface UtilisateurServiceInterface {

    //Utilisateur en générale
    void editerCompteSimple(ProfileUtilisateurRequest utilisateurInscriptionRequest);
    UtilisateurResponse voirProfile();
    void changerMotDePasse();

    //Client
    void devenirPro(DevenirProRequest devenirProRequest);


    //pro
    void changerMetier(String nomMetier);

    //admin
    void bloquerCompte(String email);
    void debloquerCompte(String email);
    void voir(String email);

}
