package com.pfe.backend.controller;

import java.util.List;
import jakarta.validation.Valid;
import com.pfe.backend.dto.request.FiliereRequest;
import com.pfe.backend.dto.response.FiliereResponse;
import com.pfe.backend.service.FiliereService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filieres")
@RequiredArgsConstructor

public class FiliereController {

    private final FiliereService filiereService;

    @GetMapping//liste tous les filieres
    public ResponseEntity<List<FiliereResponse>> getAll() {
        return ResponseEntity.ok(filiereService.getAll());
    }

    @GetMapping("/{id}")//filiere par son id
    public ResponseEntity<FiliereResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(filiereService.getById(id));
    }

    @PostMapping//creer une filiere
    public ResponseEntity<FiliereResponse> create(@RequestBody @Valid FiliereRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(filiereService.create(request));
    }

    @PutMapping("/{id}")//modifier une filiere
    public ResponseEntity<FiliereResponse> update(@PathVariable Long id,
                                                   @RequestBody @Valid FiliereRequest request) {
        return ResponseEntity.ok(filiereService.update(id, request));
    }

    @DeleteMapping("/{id}")//supprimer une filiere
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        filiereService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
