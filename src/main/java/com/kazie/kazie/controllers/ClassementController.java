package com.kazie.kazie.controllers;

import com.kazie.kazie.models.dtos.responses.CategorieResponse;
import com.kazie.kazie.models.dtos.responses.MetierResponse;
import com.kazie.kazie.models.dtos.responses.ProfesionnelResponse;
import com.kazie.kazie.models.dtos.responses.RealisationResponse;
import com.kazie.kazie.models.entities.Categorie;
import com.kazie.kazie.models.entities.Metier;
import com.kazie.kazie.models.entities.Professionnel;
import com.kazie.kazie.models.mappers.CategorieMapper;
import com.kazie.kazie.models.mappers.MetierMapper;
import com.kazie.kazie.models.mappers.RealisationMapper;
import com.kazie.kazie.repositories.CategorieRepository;
import com.kazie.kazie.repositories.MetierRepository;
import com.kazie.kazie.repositories.ProfessionnelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
@RestController
@RequestMapping("/api/classement")
@RequiredArgsConstructor
public class ClassementController {

    private final CategorieRepository categorieRepository;
    private final MetierRepository metierRepository;
    private final ProfessionnelRepository professionnelRepository;

    private final CategorieMapper categorieMapper;
    private final MetierMapper metierMapper;
    private final RealisationMapper realisationMapper;

    @GetMapping("/categories")
    public List<CategorieResponse> topCategories() {
        return categorieRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Categorie::getNombreVue).reversed())
                .limit(10)
                .map(categorieMapper::enDtos)
                .toList();
    }

    @GetMapping("/metiers")
    public List<MetierResponse> topMetiers() {
        return metierRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Metier::getNombreVue).reversed())
                .limit(10)
                .map(m -> metierMapper.enDtos(m, m.getCategorie().getNom()))
                .toList();
    }

    @GetMapping("/professionnels")
    public List<ProfesionnelResponse> topProfessionnels() {
        return professionnelRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Professionnel::getNombreVue).reversed())
                .limit(10)
                .map(pro -> {
                    ProfesionnelResponse response = new ProfesionnelResponse();
                    response.setNom(pro.getUtilisateur().getNom());
                    response.setPrenom(pro.getUtilisateur().getPrenom());
                    response.setAdresse(pro.getAdresse());
                    response.setEmail(pro.getUtilisateur().getEmail());
                    response.setNumero(pro.getUtilisateur().getNumero());
                    response.setNomMetier(pro.getMetier().getNom());
                    response.setUrlProfile(pro.getUtilisateur().getUrlProfile());
                    response.setNombreVue(pro.getNombreVue());
                    response.setNote(pro.getNote());
                    // Les réalisations
                    List<RealisationResponse> realisations = pro.getRealisations().stream()
                            .map(r -> realisationMapper.enDtos(r))
                            .toList();
                    response.setRealisationResponses(realisations);
                    return response;
                })
                .toList();
    }
}

