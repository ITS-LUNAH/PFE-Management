package com.pfe.backend.dto.request;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EtapeRequest {
    @NotBlank
    private String nom;
    private LocalDate datePrevue;
    private LocalDate dateRealisation;
    private Long stageId;
}
