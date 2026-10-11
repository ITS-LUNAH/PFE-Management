package com.pfe.backend.repository;

import java.util.List;
import com.pfe.backend.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Long> {
    List<Tache> findByStageId(Long stageId);
}
