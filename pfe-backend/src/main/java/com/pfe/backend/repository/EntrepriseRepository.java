package com.pfe.backend.repository;

import java.util.List;
import java.util.Optional;
import com.pfe.backend.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
    List<Entreprise> findByVille(String ville);
    List<Entreprise> findByPays(String pays);
    Optional<Entreprise> findByNom(String nom);
}
