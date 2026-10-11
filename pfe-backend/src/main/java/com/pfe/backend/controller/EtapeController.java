package com.pfe.backend.controller;

import java.util.List;
import com.pfe.backend.dto.request.EtapeRequest;
import com.pfe.backend.dto.response.EtapeResponse;
import com.pfe.backend.service.EtapeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/etapes")
@RequiredArgsConstructor

public class EtapeController {

    private final EtapeService etapeService;

    @GetMapping("/stage/{stageId}")//recuperer les etapes d'un stage
    public ResponseEntity<List<EtapeResponse>> getByStage(@PathVariable Long stageId) {
        return ResponseEntity.ok(etapeService.getByStage(stageId));
    }

    @PostMapping//creer une etape
    public ResponseEntity<EtapeResponse> create(@RequestBody @Valid EtapeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(etapeService.create(request));
    }

    @DeleteMapping("/{id}")//supprimer une etape
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        etapeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}