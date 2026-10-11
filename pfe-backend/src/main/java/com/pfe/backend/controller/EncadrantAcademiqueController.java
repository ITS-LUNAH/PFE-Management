package com.pfe.backend.controller;

import java.util.List;
import com.pfe.backend.dto.request.EncadrantAcademiqueRequest;
import com.pfe.backend.dto.response.EncadrantAcademiqueResponse;
import com.pfe.backend.service.EncadrantAcademiqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/encadrants-academiques")
@RequiredArgsConstructor

public class EncadrantAcademiqueController {

    private final EncadrantAcademiqueService encadrantAcademiqueService;

    @GetMapping // recuperer tous les encadrants academiques
    public ResponseEntity<List<EncadrantAcademiqueResponse>> getAll() {
        return ResponseEntity.ok(encadrantAcademiqueService.getAll());
    }

    @GetMapping("/{id}")// recuperer un encadrant par son id
    public ResponseEntity<EncadrantAcademiqueResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(encadrantAcademiqueService.getById(id));
    }

    @PostMapping//creer un nouveau encadrant
    public ResponseEntity<EncadrantAcademiqueResponse> create(
            @RequestBody @Valid EncadrantAcademiqueRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(encadrantAcademiqueService.create(request));
    }

    @PutMapping("/{id}")//modifier un encadrant
    public ResponseEntity<EncadrantAcademiqueResponse> update(@PathVariable Long id,
            @RequestBody @Valid EncadrantAcademiqueRequest request) {
        return ResponseEntity.ok(encadrantAcademiqueService.update(id, request));
    }

    @DeleteMapping("/{id}")// supprimer un encadrant
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        encadrantAcademiqueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}