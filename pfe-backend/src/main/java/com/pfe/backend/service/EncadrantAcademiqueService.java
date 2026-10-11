package com.pfe.backend.service;

import java.util.List;
import java.util.stream.Collectors;
import com.pfe.backend.dto.request.EncadrantAcademiqueRequest;
import com.pfe.backend.dto.response.EncadrantAcademiqueResponse;
import com.pfe.backend.model.EncadrantAcademique;
import com.pfe.backend.repository.EncadrantAcademiqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncadrantAcademiqueService {

    private final EncadrantAcademiqueRepository encadrantAcadRepository;

    public List<EncadrantAcademiqueResponse> getAll() {
        return encadrantAcadRepository.findAll()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public EncadrantAcademiqueResponse getById(Long id) {
        return toResponse(encadrantAcadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Encadrant académique non trouvé")));
    }

    public EncadrantAcademiqueResponse create(EncadrantAcademiqueRequest request) {
        EncadrantAcademique ea = new EncadrantAcademique();
        ea.setNom(request.getNom());
        ea.setDepartement(request.getDepartement());
        ea.setEtablissement(request.getEtablissement());
        return toResponse(encadrantAcadRepository.save(ea));
    }

    public EncadrantAcademiqueResponse update(Long id, EncadrantAcademiqueRequest request) {
        EncadrantAcademique ea = encadrantAcadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Encadrant académique non trouvé"));
        ea.setNom(request.getNom());
        ea.setDepartement(request.getDepartement());
        ea.setEtablissement(request.getEtablissement());
        return toResponse(encadrantAcadRepository.save(ea));
    }

    public void delete(Long id) {
        encadrantAcadRepository.deleteById(id);
    }

    private EncadrantAcademiqueResponse toResponse(EncadrantAcademique ea) {
        EncadrantAcademiqueResponse res = new EncadrantAcademiqueResponse();
        res.setId(ea.getId());
        res.setNom(ea.getNom());
        res.setDepartement(ea.getDepartement());
        res.setEtablissement(ea.getEtablissement());
        return res;
    }
}