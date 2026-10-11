package com.pfe.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EtudiantRequest {
    @NotBlank @Email
    private String email;
    @NotBlank
    private String cne;
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    private String tel;
    private Long filiereId;   // ← juste l'ID, pas l'objet entier
}
