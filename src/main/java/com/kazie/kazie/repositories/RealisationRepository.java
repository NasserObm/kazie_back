package com.kazie.kazie.repositories;

import com.kazie.kazie.models.entities.Professionnel;
import com.kazie.kazie.models.entities.Realisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RealisationRepository extends JpaRepository<Realisation,Long> {
}
