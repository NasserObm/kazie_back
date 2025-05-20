package com.kazie.kazie.models.mappers;

import com.kazie.kazie.exceptions.ResourceNotFoundException;
import com.kazie.kazie.models.dtos.requests.CategorieRequest;
import com.kazie.kazie.models.dtos.responses.CategorieResponse;
import com.kazie.kazie.models.dtos.responses.MetierResponse;
import com.kazie.kazie.models.entities.Categorie;
import com.kazie.kazie.models.entities.Metier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategorieMapper {
    //Injection de dépendance par constructeur
    private final MetierMapper metierMapper;

    public Categorie enEntite(CategorieRequest categorieRequest){
        Categorie categorie= new  Categorie();
        categorie.setNom(categorieRequest.getNom());
        categorie.setDescription(categorieRequest.getDescription());
        categorie.setUrlIcone(categorieRequest.getUrlIcone());
        categorie.setNombreVue(0);
        categorie.setPopulaire(false);

      return categorie;
    }

    public CategorieResponse enDtos(Categorie categorie){
        CategorieResponse categorieResponse =new CategorieResponse();
                categorieResponse.setNom(categorie.getNom());
                categorieResponse.setDescription(categorie.getDescription());
                categorieResponse.setUrlIcone(categorie.getUrlIcone());
                categorieResponse.setNombreVue(categorie.getNombreVue());
                categorieResponse.setPopulaire(categorie.isPopulaire());
                List<MetierResponse> metiersParCategorie=new ArrayList<>();
                for (Metier metier: categorie.getMetiers()){
                        metiersParCategorie.add(metierMapper.enDtos(metier,categorie.getNom()));
                     }
                categorieResponse.setMetierResponses(metiersParCategorie);

        return categorieResponse;
    }
}
