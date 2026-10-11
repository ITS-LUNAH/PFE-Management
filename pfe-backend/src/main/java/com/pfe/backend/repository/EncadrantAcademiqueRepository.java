package com.pfe.backend.repository;

import java.util.List;
import com.pfe.backend.model.EncadrantAcademique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncadrantAcademiqueRepository extends JpaRepository<EncadrantAcademique, Long> {
    List<EncadrantAcademique> findByDepartement(String departement);
    List<EncadrantAcademique> findByEtablissement(String etablissement);
}
