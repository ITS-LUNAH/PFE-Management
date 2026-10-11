package com.pfe.backend.dto.response;

import lombok.Data;

@Data
public class EncadrantProResponse {
    private Long id;
    private String email, tel, entrepriseNom;
    private Long entrepriseId;
}
