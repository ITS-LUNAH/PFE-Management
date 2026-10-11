package com.pfe.backend.controller;

import com.pfe.backend.dto.request.ResponsableFiliereRequest;
import com.pfe.backend.model.ResponsableFiliere;
import com.pfe.backend.service.ResponsableFiliereService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/responsables-filieres")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ResponsableFiliereController {

    private final ResponsableFiliereService responsableFiliereService;

    @GetMapping//liste tous les responsables de filieres
    public ResponseEntity<List<ResponsableFiliere>> getAll() {
        return ResponseEntity.ok(responsableFiliereService.getAll());
    }

    @PostMapping//creer un responsable de filiere
    public ResponseEntity<ResponsableFiliere> create(
            @RequestBody ResponsableFiliereRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responsableFiliereService.create(request));
    }


    @PutMapping("/{id}")//modifier un responsable de filiere
    public ResponseEntity<ResponsableFiliere> update(
        @PathVariable Long id,
        @RequestBody ResponsableFiliereRequest request) {
    return ResponseEntity.ok(responsableFiliereService.update(id, request));
}
}