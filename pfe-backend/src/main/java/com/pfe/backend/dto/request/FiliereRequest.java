package com.pfe.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FiliereRequest {
    @NotBlank
    private String intitule;
}
