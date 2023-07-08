package com.cursosdedesarrollo.backendauth.controllers;

import com.cursosdedesarrollo.backendauth.domain.User;
import com.cursosdedesarrollo.backendauth.dto.UserRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/")
public class AuthController {

    @PostMapping("/register")
    public User registrerUser(@RequestBody @Valid UserRegisterDTO userRegister){
        return new User(userRegister);
    }

    @GetMapping("/login")
    public User loginRequest(@RequestBody @Valid UserRegisterDTO userRegister){
        return new User();
    }
}
