package com.pfe.backend.dto.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class TacheResponse {
    private Long id;
    private String description;
    private LocalDate dateDebut, dateFin;
}
