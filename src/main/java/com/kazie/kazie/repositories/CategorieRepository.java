package com.kazie.kazie.repositories;

import com.kazie.kazie.models.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    //requête personalisé
     Optional<Categorie> findByNom(String nom);
     boolean existsByNom(String nom);
}
