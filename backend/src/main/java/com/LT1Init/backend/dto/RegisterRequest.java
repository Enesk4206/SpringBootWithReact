package com.LT1Init.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    private String token;
}
