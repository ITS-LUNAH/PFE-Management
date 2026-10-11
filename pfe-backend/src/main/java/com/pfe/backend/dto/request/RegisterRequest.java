package com.pfe.backend.dto.request;

import com.pfe.backend.model.User;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private User.Role role;
}

