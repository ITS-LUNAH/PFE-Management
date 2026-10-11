package com.pfe.backend.dto.response;

import lombok.Data;

@Data
public class EncadrantAcademiqueResponse {
    private Long id;
    private String nom, departement, etablissement;
}