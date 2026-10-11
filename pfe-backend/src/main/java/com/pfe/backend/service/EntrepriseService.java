package com.pfe.backend.service;

import java.util.List;
import java.util.stream.Collectors;
import com.pfe.backend.dto.request.EntrepriseRequest;
import com.pfe.backend.dto.response.EntrepriseResponse;
import com.pfe.backend.model.Entreprise;
import com.pfe.backend.repository.EntrepriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    public List<EntrepriseResponse> getAll() {
        return entrepriseRepository.findAll()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public EntrepriseResponse getById(Long id) {
        return toResponse(entrepriseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée")));
    }

    public EntrepriseResponse create(EntrepriseRequest request) {
        Entreprise e = new Entreprise();
        e.setNom(request.getNom());
        e.setAdresse(request.getAdresse());
        e.setTel(request.getTel());
        e.setEmail(request.getEmail());
        e.setVille(request.getVille());
        e.setPays(request.getPays());
        e.setResponsableEmail(request.getResponsableEmail());
        return toResponse(entrepriseRepository.save(e));
    }

    public EntrepriseResponse update(Long id, EntrepriseRequest request) {
        Entreprise e = entrepriseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée"));
        e.setNom(request.getNom());
        e.setAdresse(request.getAdresse());
        e.setTel(request.getTel());
        e.setEmail(request.getEmail());
        e.setVille(request.getVille());
        e.setPays(request.getPays());
        e.setResponsableEmail(request.getResponsableEmail());
        return toResponse(entrepriseRepository.save(e));
    }

    public void delete(Long id) {
        entrepriseRepository.deleteById(id);
    }

    private EntrepriseResponse toResponse(Entreprise e) {
        EntrepriseResponse res = new EntrepriseResponse();
        res.setId(e.getId());
        res.setNom(e.getNom());
        res.setAdresse(e.getAdresse());
        res.setTel(e.getTel());
        res.setEmail(e.getEmail());
        res.setVille(e.getVille());
        res.setPays(e.getPays());
        res.setResponsableEmail(e.getResponsableEmail());
        return res;
    }
}
