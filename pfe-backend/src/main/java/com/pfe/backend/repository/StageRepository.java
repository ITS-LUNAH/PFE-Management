package com.pfe.backend.repository;

import java.time.LocalDate;
import java.util.List;
import com.pfe.backend.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
    // Par filière (via étudiant)
    List<Stage> findByEtudiantFiliereId(Long filiereId);

    // Par année (dateDebut contient l'année)
    List<Stage> findByDateDebutBetween(LocalDate debut, LocalDate fin);

    // Par encadrant académique
    List<Stage> findByEncadrantAcademiqueId(Long encadrantId);

    // Par entreprise
    List<Stage> findByEntrepriseId(Long entrepriseId);
}
