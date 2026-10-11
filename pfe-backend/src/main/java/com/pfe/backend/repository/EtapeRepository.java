package com.pfe.backend.repository;

import java.util.List;
import com.pfe.backend.model.Etape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtapeRepository extends JpaRepository<Etape, Long> {
    List<Etape> findByStageId(Long stageId);
}
