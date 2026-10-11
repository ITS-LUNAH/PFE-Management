package com.pfe.backend.controller;

import java.util.List;
import jakarta.validation.Valid;
import com.pfe.backend.dto.request.EtudiantRequest;
import com.pfe.backend.dto.response.EtudiantResponse;
import com.pfe.backend.service.EtudiantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/etudiants")
@RequiredArgsConstructor

public class EtudiantController {

    private final EtudiantService etudiantService;

    @GetMapping//liste tous les etudiants
    public ResponseEntity<List<EtudiantResponse>> getAll() {
        return ResponseEntity.ok(etudiantService.getAll());
    }

    @GetMapping("/{id}")//etudiant par son id
    public ResponseEntity<EtudiantResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(etudiantService.getById(id));
    }

    @GetMapping("/filiere/{filiereId}")//etudiant par son filiere
    public ResponseEntity<List<EtudiantResponse>> getByFiliere(@PathVariable Long filiereId) {
        return ResponseEntity.ok(etudiantService.getByFiliere(filiereId));
    }

    @PostMapping//creer un etudiant
    public ResponseEntity<EtudiantResponse> create(@RequestBody @Valid EtudiantRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(etudiantService.create(request));
    }

    @PutMapping("/{id}")//modifier un etudiant
    public ResponseEntity<EtudiantResponse> update(@PathVariable Long id,
                                                    @RequestBody @Valid EtudiantRequest request) {
        return ResponseEntity.ok(etudiantService.update(id, request));
    }

    @DeleteMapping("/{id}")//supprimer un etudiant
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        etudiantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
