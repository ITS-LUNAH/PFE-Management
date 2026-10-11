package com.pfe.backend.dto.response;

import lombok.Data;

@Data
public class EtudiantResponse {
    private Long id;
    private String email;
    private String cne;
    private String nom;
    private String prenom;
    private String tel;
    private Long filiereId;
    private String filiereIntitule;   // juste le nom, pas l'objet
}
