package com.cursosdedesarrollo.backendauth.controllers;

import com.cursosdedesarrollo.backendauth.domain.User;
import com.cursosdedesarrollo.backendauth.dto.UserRegisterDTO;
import com.cursosdedesarrollo.backendauth.security.jwt.JwtUtils;
import com.cursosdedesarrollo.backendauth.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private JwtUtils jwtConfig;

    private UserService userService;

    @Autowired
    PasswordEncoder encoder;


    @Autowired
    AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registrerUser(@RequestBody @Valid UserRegisterDTO userRegister){
        return new User(userRegister);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginRequest(@RequestBody @Valid UserRegisterDTO userRegister){
        String username = userRegister.getUsername();
        // Aquí debes validar las credenciales del usuario.
        // Buscamos el usuario
        Optional<User> posibleUsuario = this.userService.findByUsername(username);
        // comprobamos si existe el usuario
        if (posibleUsuario.isPresent()) {
            // generamos el token JWT
            String token = jwtConfig.generateTokenFromUsername(username);
            return ResponseEntity.ok(token);
        } else {
            // Nota: Por motivos de seguridad, no debes indicar si el error fue por usuario incorrecto o contraseña incorrecta.
            // Simplemente devuelve UNAUTHORIZED para ambos casos.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
