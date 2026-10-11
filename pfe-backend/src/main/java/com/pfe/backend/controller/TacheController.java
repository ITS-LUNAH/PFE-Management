package com.pfe.backend.controller;

import java.util.List;
import com.pfe.backend.dto.request.TacheRequest;
import com.pfe.backend.dto.response.TacheResponse;
import com.pfe.backend.service.TacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/taches")
@RequiredArgsConstructor

public class TacheController {

    private final TacheService tacheService;

    @GetMapping("/stage/{stageId}")//taches par leur stage
    public ResponseEntity<List<TacheResponse>> getByStage(@PathVariable Long stageId) {
        return ResponseEntity.ok(tacheService.getByStage(stageId));
    }

    @PostMapping//creer une tache
    public ResponseEntity<TacheResponse> create(@RequestBody @Valid TacheRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tacheService.create(request));
    }

    @DeleteMapping("/{id}")//supprimer une tache
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tacheService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
