package com.pfe.backend.service;

import java.util.List;
import java.util.stream.Collectors;
import com.pfe.backend.dto.request.EncadrantProRequest;
import com.pfe.backend.dto.response.EncadrantProResponse;
import com.pfe.backend.model.EncadrantPro;
import com.pfe.backend.repository.EncadrantProRepository;
import com.pfe.backend.repository.EntrepriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncadrantProService {

    private final EncadrantProRepository encadrantProRepository;
    private final EntrepriseRepository entrepriseRepository;

    public List<EncadrantProResponse> getAll() {
        return encadrantProRepository.findAll()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<EncadrantProResponse> getByEntreprise(Long entrepriseId) {
        return encadrantProRepository.findByEntrepriseId(entrepriseId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public EncadrantProResponse create(EncadrantProRequest request) {
        EncadrantPro ep = new EncadrantPro();
        ep.setEmail(request.getEmail());
        ep.setTel(request.getTel());
        ep.setEntreprise(entrepriseRepository.findById(request.getEntrepriseId())
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée")));
        return toResponse(encadrantProRepository.save(ep));
    }

    public void delete(Long id) {
        encadrantProRepository.deleteById(id);
    }

    private EncadrantProResponse toResponse(EncadrantPro ep) {
        EncadrantProResponse res = new EncadrantProResponse();
        res.setId(ep.getId());
        res.setEmail(ep.getEmail());
        res.setTel(ep.getTel());
        if (ep.getEntreprise() != null){
            res.setEntrepriseNom(ep.getEntreprise().getNom());
            res.setEntrepriseId(ep.getEntreprise().getId());
        }
        return res;
    }
}
