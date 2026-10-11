package com.pfe.backend.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StageResponse {
    private Long id;
    private String sujet;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String description;
    private String objectifs;
    private String solution;
    private String demarche;
    private String outils;

    private String etudiantNom;
    private String etudiantPrenom;
    private String entrepriseNom;
    private String encadrantAcademiqueNom;
    private String encadrantProEmail;

    private Long etudiantId;
    private Long entrepriseId;
    private Long encadrantAcademiqueId;
    private Long encadrantProId;
}
