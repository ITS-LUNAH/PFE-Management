package com.pfe.backend.dto.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class EtapeResponse {
    private Long id;
    private String nom;
    private LocalDate datePrevue, dateRealisation;
}

