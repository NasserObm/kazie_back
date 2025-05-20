package com.kazie.kazie.repositories;

import com.kazie.kazie.models.entities.Utilisateur;
import jdk.jshell.execution.Util;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    //requête personalisé
    boolean existsByEmail(String email);
    Optional<Utilisateur> findByEmail(String email);
}
