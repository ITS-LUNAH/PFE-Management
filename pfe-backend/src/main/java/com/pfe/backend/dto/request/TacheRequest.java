package com.pfe.backend.dto.request;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TacheRequest {
    @NotBlank
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Long stageId;
}
