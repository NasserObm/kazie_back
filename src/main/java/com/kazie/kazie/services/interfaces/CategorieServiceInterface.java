package com.kazie.kazie.services.interfaces;

import com.kazie.kazie.models.dtos.requests.CategorieRequest;
import com.kazie.kazie.models.dtos.responses.CategorieResponse;

import java.util.List;

public interface CategorieServiceInterface {

    //methodes CRUD
    CategorieResponse ajouter(CategorieRequest categorieRequest);
    List<CategorieResponse> lister();
    CategorieResponse rechercherParNom(String nom);
    CategorieResponse editerParNom(String nom, CategorieRequest categorieRequest);
    void supprimerParNom(String nom);

    //methodes logique metiers
    void  voir(String nomCategorie);
}
