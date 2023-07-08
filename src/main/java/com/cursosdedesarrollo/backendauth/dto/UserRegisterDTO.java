package com.cursosdedesarrollo.backendauth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterDTO {
    private String username;
    private String password;
    private String email;
}
