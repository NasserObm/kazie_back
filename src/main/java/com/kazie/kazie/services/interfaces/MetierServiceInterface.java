package com.kazie.kazie.services.interfaces;

import com.kazie.kazie.models.dtos.requests.MetierRequest;
import com.kazie.kazie.models.dtos.responses.MetierResponse;

import java.util.List;

public interface MetierServiceInterface {
    //methode CRUD
    MetierResponse ajouter(MetierRequest metierRequest);
    List<MetierResponse> lister();
    MetierResponse rechercherParNom(String nom);
    MetierResponse editerParNom(String nom, MetierRequest metierRequest);
    void supprimerParNom(String nom);

    //logique metier
    //lister tous les metiers d'une catégorie
    List<MetierResponse> listerParCategorie(String nomCategorie);
    void voirMetier(String nomMetier);
}
