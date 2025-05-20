package com.kazie.kazie.repositories;

import com.kazie.kazie.models.entities.Utilisateur;
import com.kazie.kazie.models.entities.Vue;
import com.kazie.kazie.models.enums.TypeVue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VueRepository extends JpaRepository<Vue, Long> {
    boolean existsByUtilisateurAndTypeAndCibleNom(Utilisateur utilisateur, TypeVue type, String cibleNom);
}

