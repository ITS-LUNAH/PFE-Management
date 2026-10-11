package com.pfe.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Entreprise {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;
    private String tel;
    private String email;
    private String ville;
    private String pays;
    private String responsableEmail;

    @OneToMany(mappedBy = "entreprise")
    private List<Stage> stages;

    @OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL)
    private List<EncadrantPro> encadrants;
}
