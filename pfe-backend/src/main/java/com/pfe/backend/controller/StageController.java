package com.pfe.backend.controller;

import java.time.LocalDate;
import java.util.List;
import com.pfe.backend.dto.request.StageRequest;
import com.pfe.backend.dto.response.StageResponse;
import com.pfe.backend.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/stages")
@RequiredArgsConstructor

public class StageController {

    private final StageService stageService;

    @GetMapping //la liste complete de tous les stages
    public ResponseEntity<List<StageResponse>> getAll() {
        return ResponseEntity.ok(stageService.getAll());
    }

    @GetMapping("/{id}")// stage par id
    public ResponseEntity<StageResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(stageService.getById(id));
    }

    //stage associes a une filiere
    @GetMapping("/filiere/{filiereId}")
    public ResponseEntity<List<StageResponse>> getByFiliere(@PathVariable Long filiereId) {
        return ResponseEntity.ok(stageService.getByFiliere(filiereId));
    }
    // stage selon une annee
    @GetMapping("/annee/{year}")
    public ResponseEntity<List<StageResponse>> getByYear(@PathVariable int year) {
        LocalDate debut = LocalDate.of(year, 1, 1);
        LocalDate fin = LocalDate.of(year, 12, 31);
        return ResponseEntity.ok(stageService.getByPeriode(debut, fin));
    }

    @PostMapping // creer un nouveau stage
    public ResponseEntity<StageResponse> create(@RequestBody @Valid StageRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stageService.create(request));
    }

    @PutMapping("/{id}")//modifier un stage
    public ResponseEntity<StageResponse> update(@PathVariable Long id,
                                                 @RequestBody @Valid StageRequest request) {
        return ResponseEntity.ok(stageService.update(id, request));
    }

    @DeleteMapping("/{id}")//supprimer un stage
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Assigner encadrant académique
    @PostMapping("/{id}/assign-academic/{encadrantId}")
    public ResponseEntity<StageResponse> assignAcademic(@PathVariable Long id,
                                                         @PathVariable Long encadrantId) {
        return ResponseEntity.ok(stageService.assignEncadrantAcademique(id, encadrantId));
    }

    // Assigner encadrant pro
    @PostMapping("/{id}/assign-professional/{encadrantId}")
    public ResponseEntity<StageResponse> assignProfessional(@PathVariable Long id,
                                                             @PathVariable Long encadrantId) {
        return ResponseEntity.ok(stageService.assignEncadrantPro(id, encadrantId));
    }

    // Générer PDF
    @GetMapping("/{id}/report")
    public ResponseEntity<byte[]> generateReport(@PathVariable Long id) {
        byte[] pdf = stageService.generateReport(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=stage_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
