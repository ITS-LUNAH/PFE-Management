package com.pfe.backend.repository;

import java.util.List;
import java.util.Optional;
import com.pfe.backend.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    List<Etudiant> findByFiliereId(Long filiereId);
    Optional<Etudiant> findByEmail(String email);
    Optional<Etudiant> findByCne(String cne);
}
