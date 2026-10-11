package com.pfe.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EncadrantAcademiqueRequest {
    @NotBlank
    private String nom;
    private String departement;
    private String etablissement;
}
