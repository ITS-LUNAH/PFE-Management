package com.pfe.backend.dto.request;

import lombok.Data;

@Data
public class ResponsableFiliereRequest {
    private String nom;
    private String prenom;
    private String grade;
    private String email;
    private String tel;
    private Long filiereId;
}