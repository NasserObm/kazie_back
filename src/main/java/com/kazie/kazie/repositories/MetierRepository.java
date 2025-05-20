package com.kazie.kazie.repositories;

import com.kazie.kazie.models.entities.Metier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetierRepository extends JpaRepository<Metier,Long> {
    Optional<Metier> findByNom(String nom);
    boolean existsByNom(String nom);
}
