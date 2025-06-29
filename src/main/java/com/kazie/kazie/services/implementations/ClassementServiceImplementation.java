package com.kazie.kazie.services.implementations;

import com.kazie.kazie.models.entities.Categorie;
import com.kazie.kazie.models.entities.Metier;
import com.kazie.kazie.models.entities.Professionnel;
import com.kazie.kazie.repositories.CategorieRepository;
import com.kazie.kazie.repositories.MetierRepository;
import com.kazie.kazie.repositories.ProfessionnelRepository;
import com.kazie.kazie.repositories.UtilisateurRepository;
import com.kazie.kazie.services.interfaces.ClassementServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ClassementServiceImplementation implements ClassementServiceInterface {
    //injection de dépendance par constructeur
    private final CategorieRepository categorieRepository;

    public ClassementServiceImplementation(CategorieRepository categorieRepository, MetierRepository metierRepository, UtilisateurRepository utilisateurRepository, ProfessionnelRepository professionnelRepository) {
        this.categorieRepository = categorieRepository;
        this.metierRepository = metierRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.professionnelRepository = professionnelRepository;
    }

    private final MetierRepository metierRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ProfessionnelRepository professionnelRepository;

    //Automatisation
    @Scheduled(cron = "0 0 * * * *") // Toutes les heures, à 00 minutes
    public void mettreAJourClassements() {
        mettreAJourTop10Categories();
        mettreAJourTop10Metiers();
        mettreAJourTop10Professionnels();
        System.out.println("✅ Classements mis à jour automatiquement.");
    }

    public void mettreAJourTop10Metiers() {
        List<Metier> metiers = metierRepository.findAll();
        metiers.sort((m1, m2) -> Integer.compare(m2.getNombreVue(), m1.getNombreVue()));
        for (int i = 0; i < metiers.size(); i++) {
            metiers.get(i).setPopulaire(i < 10);
        }
        metierRepository.saveAll(metiers);
    }

    public void mettreAJourTop10Professionnels() {
        List<Professionnel> pros = professionnelRepository.findAll();
        pros.sort((p1, p2) -> Integer.compare(p2.getNombreVue(), p1.getNombreVue()));
        for (int i = 0; i < pros.size(); i++) {
            pros.get(i).setPopulaire(i < 10);
        }
        professionnelRepository.saveAll(pros);
    }

    @Override
    public void mettreAJourTop10Categories() {
        List<Categorie> toutes = categorieRepository.findAll();

        // Trier par nombre de vues décroissant
        toutes.sort((c1, c2) -> Integer.compare(c2.getNombreVue(), c1.getNombreVue()));

        // Réinitialiser tous à false
        toutes.forEach(c -> c.setPopulaire(false));

        // Marquer les 10 premiers comme populaires
        for (int i = 0; i < Math.min(10, toutes.size()); i++) {
            toutes.get(i).setPopulaire(true);
        }

        categorieRepository.saveAll(toutes);
    }

}
