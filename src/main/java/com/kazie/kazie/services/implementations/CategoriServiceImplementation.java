package com.kazie.kazie.services.implementations;

import com.kazie.kazie.exceptions.ResourceNotFoundException;
import com.kazie.kazie.models.dtos.requests.CategorieRequest;
import com.kazie.kazie.models.dtos.responses.CategorieResponse;
import com.kazie.kazie.models.dtos.responses.MetierResponse;
import com.kazie.kazie.models.entities.Categorie;
import com.kazie.kazie.models.entities.Metier;
import com.kazie.kazie.models.enums.TypeVue;
import com.kazie.kazie.models.mappers.CategorieMapper;
import com.kazie.kazie.repositories.CategorieRepository;
import com.kazie.kazie.repositories.MetierRepository;
import com.kazie.kazie.services.interfaces.CategorieServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriServiceImplementation implements CategorieServiceInterface {
    //Injections des dépendances par constructeur
    private final CategorieMapper categorieMapper;
    private final CategorieRepository categorieRepository;
    private final MetierRepository metierRepository;
    private final VueServiceImpl vueService;

    //Implémentations du CRUD
    @Override
    public CategorieResponse ajouter(CategorieRequest categorieRequest) {
        Categorie categorie=categorieMapper.enEntite(categorieRequest);
        if (categorieRepository.existsByNom(categorieRequest.getNom())){
            throw new ResourceNotFoundException("Une catégorie possède déjà ce nom");
        }
        List<Metier> metiersParCategorie=new ArrayList<>();
        for (Metier metier:metierRepository.findAll()){
            if (metier.getCategorie().getNom().equals(categorieRequest.getNom())){
                metiersParCategorie.add(metier);
            }
        }
        categorie.setMetiers(metiersParCategorie);
        categorieRepository.save(categorie);
        return categorieMapper.enDtos(categorie);
    }

    @Override
    public List<CategorieResponse> lister() {
       return categorieRepository.findAll().stream().map(categorieMapper::enDtos).toList();
    }

    @Override
    public CategorieResponse rechercherParNom(String nom) {
        Categorie categorie=categorieRepository.findByNom(nom).orElseThrow(
                ()-> new ResourceNotFoundException("Aucune Categories avec ce nom")
        );
        return categorieMapper.enDtos(categorie);
    }

    @Override
    public CategorieResponse editerParNom(String nom, CategorieRequest categorieRequest) {
        Categorie categorie=categorieRepository.findByNom(nom).orElseThrow(
                ()-> new RuntimeException("Aucune Categories avec ce nom")
        );
        if (categorieRepository.existsByNom(categorieRequest.getNom())){
            throw new ResourceNotFoundException("Une categorie possède déjà ce nom");
        }
        categorie.setNom(categorieRequest.getNom());
        categorie.setDescription(categorieRequest.getDescription());
        categorie.setUrlIcone(categorieRequest.getUrlIcone());
        categorieRepository.save(categorie);
        return categorieMapper.enDtos(categorie);
    }

    @Override
    public void supprimerParNom(String nom) {
        categorieRepository.delete(categorieRepository.findByNom(nom).orElseThrow(
                ()->new ResourceNotFoundException("Aucune Categories avec ce nom")
        ));
    }

    @Override
    public void voir(String nomCategorie) {
        Categorie categorie = categorieRepository.findByNom(nomCategorie)
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie introuvable"));// ta méthode perso

        boolean nouvelleVue = vueService.ajouterVueSiNouvelle(TypeVue.CATEGORIE, nomCategorie);

        if (nouvelleVue) {
            categorie.ajouterVue();
            categorieRepository.save(categorie);
        }
    }


}
