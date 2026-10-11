package com.pfe.backend.controller;

import java.util.List;
import com.pfe.backend.dto.request.EntrepriseRequest;
import com.pfe.backend.dto.response.EntrepriseResponse;
import com.pfe.backend.service.EntrepriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/entreprises")
@RequiredArgsConstructor

public class EntrepriseController {

    private final EntrepriseService entrepriseService;

    @GetMapping//recuperer tous les entreprise
    public ResponseEntity<List<EntrepriseResponse>> getAll() {
        return ResponseEntity.ok(entrepriseService.getAll());
    }

    @GetMapping("/{id}")// entreprise par id
    public ResponseEntity<EntrepriseResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(entrepriseService.getById(id));
    }

    @PostMapping//creer entreprise
    public ResponseEntity<EntrepriseResponse> create(@RequestBody @Valid EntrepriseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(entrepriseService.create(request));
    }

    @PutMapping("/{id}")// modifier entreprise
    public ResponseEntity<EntrepriseResponse> update(@PathVariable Long id,
                                                      @RequestBody @Valid EntrepriseRequest request) {
        return ResponseEntity.ok(entrepriseService.update(id, request));
    }

    @DeleteMapping("/{id}")// supprimer entreprise
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entrepriseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
