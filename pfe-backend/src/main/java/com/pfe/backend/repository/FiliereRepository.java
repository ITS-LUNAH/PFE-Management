package com.pfe.backend.repository;

import java.util.Optional;
import com.pfe.backend.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    Optional<Filiere> findByIntitule(String intitule);
}
