package com.pfe.backend.dto.response;

import lombok.Data;

@Data
public class FiliereResponse {
    private Long id;
    private String intitule;
    private String responsableNom;    // extrait du ResponsableFiliere
    private int nombreEtudiants;
}
