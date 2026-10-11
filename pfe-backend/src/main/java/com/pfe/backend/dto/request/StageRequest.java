package com.pfe.backend.dto.request;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StageRequest {
    @NotBlank
    private String sujet;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String description;
    private String objectifs;
    private String solution;
    private String demarche;
    private String outils;

    private Long etudiantId;
    private Long entrepriseId;
    private Long encadrantAcademiqueId;
    private Long encadrantProId;
}
