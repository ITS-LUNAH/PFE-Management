package com.pfe.backend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import com.pfe.backend.dto.request.StageRequest;
import com.pfe.backend.dto.response.StageResponse;
import com.pfe.backend.model.Etudiant;
import com.pfe.backend.model.Stage;
import com.pfe.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.*;
//import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class StageService {

    private final StageRepository stageRepository;
    private final EtudiantRepository etudiantRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final EncadrantAcademiqueRepository encadrantAcadRepository;
    private final EncadrantProRepository encadrantProRepository;

    //  injecter PdfService
    private final PdfService pdfService;

    
    public byte[] generateReport(Long stageId) {
        return pdfService.generateStageReport(stageId);
    }

    public List<StageResponse> getAll() {
        return stageRepository.findAll().stream()
                .map(this::toResponse).collect(Collectors.toList());
    }

    public List<StageResponse> getByFiliere(Long filiereId) {
        return stageRepository.findByEtudiantFiliereId(filiereId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public StageResponse create(StageRequest request) {
        // vérifier si l'étudiant a déjà un stage
    Etudiant etudiant = etudiantRepository.findById(request.getEtudiantId())
            .orElseThrow(() -> new RuntimeException("Etudiant non trouvé"));

    if (etudiant.getStage() != null) {
        throw new RuntimeException("Cet étudiant a déjà un stage assigné");
    }

        Stage stage = new Stage();
        stage.setSujet(request.getSujet());
        stage.setDateDebut(request.getDateDebut());
        stage.setDateFin(request.getDateFin());
        stage.setDescription(request.getDescription());
        stage.setObjectifs(request.getObjectifs());
        stage.setSolution(request.getSolution());
        stage.setDemarche(request.getDemarche());
        stage.setOutils(request.getOutils());

        // Associations par ID

        stage.setEtudiant(etudiantRepository.findById(request.getEtudiantId())
            .orElseThrow(() -> new RuntimeException("Etudiant non trouvé")));


            if (request.getEntrepriseId() != null)
        stage.setEntreprise(entrepriseRepository.findById(request.getEntrepriseId())
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée")));

    if (request.getEncadrantAcademiqueId() != null)
        stage.setEncadrantAcademique(encadrantAcadRepository.findById(request.getEncadrantAcademiqueId())
                .orElseThrow(() -> new RuntimeException("Encadrant académique non trouvé")));

    if (request.getEncadrantProId() != null)
        stage.setEncadrantPro(encadrantProRepository.findById(request.getEncadrantProId())
                .orElseThrow(() -> new RuntimeException("Encadrant pro non trouvé")));

    return toResponse(stageRepository.save(stage));
    }

    public void delete(Long id) {
    Stage stage = stageRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Stage non trouvé"));

    // on dissocier l'étudiant avant suppression
    if (stage.getEtudiant() != null) {
        Etudiant etudiant = stage.getEtudiant();
        etudiant.setStage(null);
        etudiantRepository.save(etudiant);
    }

    stageRepository.deleteById(id);
}

//convertit une entite Stage en objet de reponse (DTO) destiné e etre envoyé au client.
    private StageResponse toResponse(Stage s) {
        StageResponse res = new StageResponse();
        res.setId(s.getId());
        res.setSujet(s.getSujet());
        res.setDateDebut(s.getDateDebut());
        res.setDateFin(s.getDateFin());
        res.setDescription(s.getDescription());
        res.setObjectifs(s.getObjectifs());
        res.setSolution(s.getSolution());
        res.setDemarche(s.getDemarche());
        res.setOutils(s.getOutils());
        if (s.getEtudiant() != null) {
        res.setEtudiantId(s.getEtudiant().getId());        
        res.setEtudiantNom(s.getEtudiant().getNom());
        res.setEtudiantPrenom(s.getEtudiant().getPrenom());
    }
    if (s.getEntreprise() != null) {
        res.setEntrepriseId(s.getEntreprise().getId());     
        res.setEntrepriseNom(s.getEntreprise().getNom());
    }
    if (s.getEncadrantAcademique() != null) {
        res.setEncadrantAcademiqueId(s.getEncadrantAcademique().getId());  
        res.setEncadrantAcademiqueNom(s.getEncadrantAcademique().getNom());
    }
    if (s.getEncadrantPro() != null) {
        res.setEncadrantProId(s.getEncadrantPro().getId());  
        res.setEncadrantProEmail(s.getEncadrantPro().getEmail());
    }
    return res;
    }


public StageResponse getById(Long id) {
    return toResponse(stageRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Stage non trouvé")));
}

public StageResponse update(Long id, StageRequest request) {
    Stage stage = stageRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Stage non trouvé"));
    stage.setSujet(request.getSujet());
    stage.setDateDebut(request.getDateDebut());
    stage.setDateFin(request.getDateFin());
    stage.setDescription(request.getDescription());
    stage.setObjectifs(request.getObjectifs());
    stage.setSolution(request.getSolution());
    stage.setDemarche(request.getDemarche());
    stage.setOutils(request.getOutils());
    return toResponse(stageRepository.save(stage));
}

// Méthode de filtrage par période
public List<StageResponse> getByPeriode(LocalDate debut, LocalDate fin) {
    return stageRepository.findByDateDebutBetween(debut, fin)
            .stream().map(this::toResponse).collect(Collectors.toList());
}

// Méthodes d'assignation d'encadrants

public StageResponse assignEncadrantAcademique(Long stageId, Long encadrantId) {
    Stage stage = stageRepository.findById(stageId)
            .orElseThrow(() -> new RuntimeException("Stage non trouvé"));
    stage.setEncadrantAcademique(encadrantAcadRepository.findById(encadrantId)
            .orElseThrow(() -> new RuntimeException("Encadrant académique non trouvé")));
    return toResponse(stageRepository.save(stage));
}

public StageResponse assignEncadrantPro(Long stageId, Long encadrantId) {
    Stage stage = stageRepository.findById(stageId)
            .orElseThrow(() -> new RuntimeException("Stage non trouvé"));
    stage.setEncadrantPro(encadrantProRepository.findById(encadrantId)
            .orElseThrow(() -> new RuntimeException("Encadrant pro non trouvé")));
    return toResponse(stageRepository.save(stage));
}

}
