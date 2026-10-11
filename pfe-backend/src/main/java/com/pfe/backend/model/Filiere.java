package com.pfe.backend.model;

import jakarta.persistence.*;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Filiere {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String intitule;

    @OneToOne(mappedBy = "filiere", cascade = CascadeType.ALL)
    private ResponsableFiliere responsable;

    @OneToMany(mappedBy = "filiere")
    private List<Etudiant> etudiants;
}
