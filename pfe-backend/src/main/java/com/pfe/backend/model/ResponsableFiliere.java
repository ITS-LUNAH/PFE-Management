package com.pfe.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class ResponsableFiliere {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String grade;
    private String email;
    private String tel;

    @OneToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;
}
