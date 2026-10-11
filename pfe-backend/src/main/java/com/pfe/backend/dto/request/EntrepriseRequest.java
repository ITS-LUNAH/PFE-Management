package com.pfe.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EntrepriseRequest {
    @NotBlank
    private String nom;
    private String adresse;
    private String tel;
    private String email;
    private String ville;
    private String pays;
    private String responsableEmail;
}
