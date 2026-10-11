package com.pfe.backend.repository;

import java.util.Optional;
import com.pfe.backend.model.ResponsableFiliere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResponsableFilierRepository extends JpaRepository<ResponsableFiliere, Long> {
    Optional<ResponsableFiliere> findByEmail(String email);
}
