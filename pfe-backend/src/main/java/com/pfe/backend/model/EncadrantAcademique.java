package com.pfe.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class EncadrantAcademique {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String departement;
    private String etablissement;

    @OneToMany(mappedBy = "encadrantAcademique")
    private List<Stage> stages;
}
