package com.pfe.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pfe.backend.model.Stage;
import com.pfe.backend.repository.*;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor

public class DashboardController {

    private final StageRepository stageRepository;
    private final EtudiantRepository etudiantRepository;
    private final FiliereRepository filiereRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final EtapeRepository etapeRepository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getDashboard() {
        Map<String, Object> data = new HashMap<>();
        LocalDate today = LocalDate.now();

        // Totaux généraux
        data.put("totalStages", stageRepository.count());
        data.put("totalEtudiants", etudiantRepository.count());
        data.put("totalFilieres", filiereRepository.count());
        data.put("totalEntreprises", entrepriseRepository.count());

        // Stages en cours / terminés
        List<Stage> allStages = stageRepository.findAll();
        long enCours = allStages.stream()
                .filter(s -> s.getDateDebut() != null && s.getDateFin() != null
                        && !today.isBefore(s.getDateDebut())
                        && !today.isAfter(s.getDateFin()))
                .count();
        long termines = allStages.stream()
                .filter(s -> s.getDateFin() != null && today.isAfter(s.getDateFin()))
                .count();
        data.put("stagesEnCours", enCours);
        data.put("stagesTermines", termines);

        // Stages par filière
        Map<String, Long> stagesParFiliere = new HashMap<>();
        filiereRepository.findAll().forEach(f ->
                stagesParFiliere.put(f.getIntitule(),
                        stageRepository.findByEtudiantFiliereId(f.getId()).stream().count()));
        data.put("stagesParFiliere", stagesParFiliere);

        // Répartition par entreprise
        Map<String, Long> stagesParEntreprise = new HashMap<>();
        entrepriseRepository.findAll().forEach(e ->
                stagesParEntreprise.put(e.getNom(),
                        stageRepository.findByEntrepriseId(e.getId()).stream().count()));
        data.put("stagesParEntreprise", stagesParEntreprise);

        // Progression des étapes
        long totalEtapes = etapeRepository.count();
        long etapesRealisees = etapeRepository.findAll().stream()
                .filter(e -> e.getDateRealisation() != null)
                .count();
        data.put("totalEtapes", totalEtapes);
        data.put("etapesRealisees", etapesRealisees);
        data.put("progressionEtapes", totalEtapes > 0
                ? Math.round((double) etapesRealisees / totalEtapes * 100) + "%"
                : "0%");

        return ResponseEntity.ok(data);
    }
}