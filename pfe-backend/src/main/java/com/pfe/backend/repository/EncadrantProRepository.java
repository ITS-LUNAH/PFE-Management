package com.pfe.backend.repository;

import java.util.List;
import java.util.Optional;
import com.pfe.backend.model.EncadrantPro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncadrantProRepository extends JpaRepository<EncadrantPro, Long> {
    List<EncadrantPro> findByEntrepriseId(Long entrepriseId);
    Optional<EncadrantPro> findByEmail(String email);
}
