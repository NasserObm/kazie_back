package com.kazie.kazie.repositories;

import com.kazie.kazie.models.entities.Professionnel;
import com.kazie.kazie.models.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessionnelRepository extends JpaRepository<Professionnel,Long> {
    Optional<Professionnel> findByUtilisateur(Utilisateur utilisateur);
}
