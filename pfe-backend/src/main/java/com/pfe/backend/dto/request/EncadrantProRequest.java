package com.pfe.backend.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class EncadrantProRequest {
    @Email
    private String email;
    private String tel;
    private Long entrepriseId;
}
