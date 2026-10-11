package com.pfe.backend.service;

import com.pfe.backend.controller.ResponsableFiliereController;
import com.pfe.backend.dto.request.ResponsableFiliereRequest;
import com.pfe.backend.model.ResponsableFiliere;
import com.pfe.backend.repository.FiliereRepository;
import com.pfe.backend.repository.ResponsableFilierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponsableFiliereService {

    private final ResponsableFilierRepository repository;
    private final FiliereRepository filiereRepository;

    public List<ResponsableFiliere> getAll() {
        return repository.findAll();
    }

    public ResponsableFiliere create(ResponsableFiliereRequest request) {
        ResponsableFiliere r = new ResponsableFiliere();
        r.setNom(request.getNom());
        r.setPrenom(request.getPrenom());
        r.setGrade(request.getGrade());
        r.setEmail(request.getEmail());
        r.setTel(request.getTel());
        if (request.getFiliereId() != null) {
            r.setFiliere(filiereRepository.findById(request.getFiliereId())
                    .orElseThrow(() -> new RuntimeException("Filière non trouvée")));
        }
        return repository.save(r);
    }


    public ResponsableFiliere update(Long id, ResponsableFiliereRequest request) {
    ResponsableFiliere r = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Responsable non trouvé"));
    r.setNom(request.getNom());
    r.setPrenom(request.getPrenom());
    r.setGrade(request.getGrade());
    r.setEmail(request.getEmail());
    r.setTel(request.getTel());
    return repository.save(r);
}
}