package com.kazie.kazie.services.implementations;

import com.kazie.kazie.exceptions.ResourceNotFoundException;
import com.kazie.kazie.models.dtos.requests.MetierRequest;
import com.kazie.kazie.models.dtos.responses.MetierResponse;
import com.kazie.kazie.models.entities.Categorie;
import com.kazie.kazie.models.entities.Metier;
import com.kazie.kazie.models.enums.TypeVue;
import com.kazie.kazie.models.mappers.MetierMapper;
import com.kazie.kazie.repositories.CategorieRepository;
import com.kazie.kazie.repositories.MetierRepository;
import com.kazie.kazie.services.interfaces.MetierServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service

public class MetierServiceImplementation implements MetierServiceInterface {
    //injection de dépandance par constructeur
    private final MetierRepository metierRepository;
    private final MetierMapper metierMapper;

    public MetierServiceImplementation(MetierRepository metierRepository, MetierMapper metierMapper, CategorieRepository categorieRepository, VueServiceImpl vueService) {
        this.metierRepository = metierRepository;
        this.metierMapper = metierMapper;
        this.categorieRepository = categorieRepository;
        this.vueService = vueService;
    }

    private final CategorieRepository categorieRepository;
    private final VueServiceImpl vueService;
    //implementation du crud
    @Override
    public MetierResponse ajouter(MetierRequest metierRequest) {
        Categorie categorie=categorieRepository.findByNom(metierRequest.getNomCategories()).orElseThrow(
                ()-> new ResourceNotFoundException("Aucune categorie ne possède ce nom")
        );
        Metier metier=metierMapper.enEntite(metierRequest,categorie);
        metierRepository.save(metier);
        return metierMapper.enDtos(metier,metier.getCategorie().getNom());
    }

    @Override
    public List<MetierResponse> lister() {
        List<MetierResponse> metierResponses=new ArrayList<>();
        for(Metier metier: metierRepository.findAll()){
            metierResponses.add(metierMapper.enDtos(metier,metier.getCategorie().getNom()));
        }
        return metierResponses;
    }

    @Override
    public MetierResponse rechercherParNom(String nom) {
        Metier metier=metierRepository.findByNom(nom).orElseThrow(
                ()-> new ResourceNotFoundException("Aucun metiers ne possède ce nom")
        );
        return metierMapper.enDtos(metier,metier.getCategorie().getNom());
    }

    @Override
    public MetierResponse editerParNom(String nom,MetierRequest metierRequest) {
        Metier metier=metierRepository.findByNom(nom).orElseThrow(
                ()->new ResourceNotFoundException("Aucun metiers ne possède ce nom")
        );
        metier.setNom(metierRequest.getNom());
        metier.setDescription(metierRequest.getDescription());
        metier.setUrlImage(metierRequest.getUrlImage());

        //Changement de catégorie
        Categorie categorie=categorieRepository.findByNom(metierRequest.getNomCategories()).orElseThrow(
                ()-> new ResourceNotFoundException("Aucune catégorie avec ce nom")
        );
        metier.setCategorie(categorie);
        metierRepository.save(metier);
        return  metierMapper.enDtos(metier,metier.getCategorie().getNom());
    }

    @Override
    public void supprimerParNom(String nom) {
        metierRepository.delete(metierRepository.findByNom(nom).orElseThrow(
                ()->new ResourceNotFoundException("Aucun metier ne possède ce nom")
        ));
    }

    @Override
    public List<MetierResponse> listerParCategorie(String nomCategorie) {
        if (!categorieRepository.existsByNom(nomCategorie)){
            throw new ResourceNotFoundException("Aucune catégorie avec ce nom");
        }
        List<MetierResponse> metiersParCategorie=new ArrayList<>();
        for (Metier metier:metierRepository.findAll()){
            if (metier.getCategorie().getNom().equals(nomCategorie)){
                metiersParCategorie.add(metierMapper.enDtos(metier,metier.getCategorie().getNom()));
            }
        }
        return metiersParCategorie;
    }

    @Override
    public void voirMetier(String nomMetier) {
        Metier metier=metierRepository.findByNom(nomMetier).orElseThrow(
                ()-> new ResourceNotFoundException("Aucun metier ne possède ce nom")
        );
        boolean nouvelleVue = vueService.ajouterVueSiNouvelle(TypeVue.CATEGORIE, nomMetier);
       if (nouvelleVue){
           metier.ajouterVue();
           metierRepository.save(metier);
       }
    }
    @Override
    public List<MetierResponse> rechercherParMotCle(String query) {
        List<Metier> metiers = metierRepository.findByNomContainingIgnoreCase(query);
        return metiers.stream()
                .map(metier -> metierMapper.enDtos(metier, metier.getCategorie().getNom()))
                .collect(Collectors.toList());
    }

}
