package com.pfe.backend.dto.response;

import lombok.Data;

@Data
public class EntrepriseResponse {
    private Long id;
    private String nom, adresse, tel, email, ville, pays, responsableEmail;
}