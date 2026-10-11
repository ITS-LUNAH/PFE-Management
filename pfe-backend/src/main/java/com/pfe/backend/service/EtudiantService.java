package com.pfe.backend.service;

import java.util.List;
import java.util.stream.Collectors;
import com.pfe.backend.dto.request.EtudiantRequest;
import com.pfe.backend.dto.response.EtudiantResponse;
import com.pfe.backend.model.Etudiant;
import com.pfe.backend.repository.EtudiantRepository;
import com.pfe.backend.repository.FiliereRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final FiliereRepository filiereRepository;

    public List<EtudiantResponse> getAll() {
        return etudiantRepository.findAll()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<EtudiantResponse> getByFiliere(Long filiereId) {
        return etudiantRepository.findByFiliereId(filiereId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public EtudiantResponse getById(Long id) {
        return toResponse(etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé")));
    }

    // Créer un nouvel étudiant
    public EtudiantResponse create(EtudiantRequest request) {
        Etudiant e = new Etudiant();
        e.setEmail(request.getEmail());
        e.setCne(request.getCne());
        e.setNom(request.getNom());
        e.setPrenom(request.getPrenom());
        e.setTel(request.getTel());
        e.setFiliere(filiereRepository.findById(request.getFiliereId())
                .orElseThrow(() -> new RuntimeException("Filiere non trouvée")));
        return toResponse(etudiantRepository.save(e));
    }

    public EtudiantResponse update(Long id, EtudiantRequest request) {
        Etudiant e = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé"));
        e.setEmail(request.getEmail());
        e.setCne(request.getCne());
        e.setNom(request.getNom());
        e.setPrenom(request.getPrenom());
        e.setTel(request.getTel());
        if (request.getFiliereId() != null)
            e.setFiliere(filiereRepository.findById(request.getFiliereId())
                    .orElseThrow(() -> new RuntimeException("Filiere non trouvée")));
        return toResponse(etudiantRepository.save(e));
    }

    public void delete(Long id) {
        etudiantRepository.deleteById(id);
    }

    // Méthode de conversion d'entité en DTO
    private EtudiantResponse toResponse(Etudiant e) {
    EtudiantResponse res = new EtudiantResponse();
    res.setId(e.getId());
    res.setEmail(e.getEmail());
    res.setCne(e.getCne());
    res.setNom(e.getNom());
    res.setPrenom(e.getPrenom());
    res.setTel(e.getTel());
    if (e.getFiliere() != null) {
        res.setFiliereId(e.getFiliere().getId());       
        res.setFiliereIntitule(e.getFiliere().getIntitule());
    }
    return res;
    }
}
