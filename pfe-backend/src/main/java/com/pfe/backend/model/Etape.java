package com.pfe.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Etape {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private LocalDate datePrevue;
    private LocalDate dateRealisation;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;
}
