package com.pfe.backend.service;

import java.util.List;
import java.util.stream.Collectors;
import com.pfe.backend.dto.request.FiliereRequest;
import com.pfe.backend.dto.response.FiliereResponse;
import com.pfe.backend.model.Filiere;
import com.pfe.backend.repository.FiliereRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FiliereService {

    private final FiliereRepository filiereRepository;

    public List<FiliereResponse> getAll() {
        return filiereRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public FiliereResponse getById(Long id) {
        Filiere f = filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filiere non trouvée"));
        return toResponse(f);
    }

    public FiliereResponse create(FiliereRequest request) {
        Filiere f = new Filiere();
        f.setIntitule(request.getIntitule());
        return toResponse(filiereRepository.save(f));
    }

    public FiliereResponse update(Long id, FiliereRequest request) {
        Filiere f = filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filiere non trouvée"));
        f.setIntitule(request.getIntitule());
        return toResponse(filiereRepository.save(f));
    }

    public void delete(Long id) {
        filiereRepository.deleteById(id);
    }

    private FiliereResponse toResponse(Filiere f) {
        FiliereResponse res = new FiliereResponse();
        res.setId(f.getId());
        res.setIntitule(f.getIntitule());
        res.setNombreEtudiants(f.getEtudiants() != null ? f.getEtudiants().size() : 0);
        if (f.getResponsable() != null)
            res.setResponsableNom(f.getResponsable().getNom());
        return res;
    }
}
