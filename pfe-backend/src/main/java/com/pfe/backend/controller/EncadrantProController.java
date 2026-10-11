package com.pfe.backend.controller;

import java.util.List;
import com.pfe.backend.dto.request.EncadrantProRequest;
import com.pfe.backend.dto.response.EncadrantProResponse;
import com.pfe.backend.service.EncadrantProService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/encadrants-pro")
@RequiredArgsConstructor

public class EncadrantProController {

    private final EncadrantProService encadrantProService;

    @GetMapping//recuperer tous les encadrants professionnels
    public ResponseEntity<List<EncadrantProResponse>> getAll() {
        return ResponseEntity.ok(encadrantProService.getAll());
    }

    @GetMapping("/entreprise/{entrepriseId}")// get encadrant par entreprise
    public ResponseEntity<List<EncadrantProResponse>> getByEntreprise(
            @PathVariable Long entrepriseId) {
        return ResponseEntity.ok(encadrantProService.getByEntreprise(entrepriseId));
    }

    @PostMapping// creer un encadrant pro
    public ResponseEntity<EncadrantProResponse> create(
            @RequestBody @Valid EncadrantProRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(encadrantProService.create(request));
    }

    @DeleteMapping("/{id}")//supprimer un encadrant pro
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        encadrantProService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
