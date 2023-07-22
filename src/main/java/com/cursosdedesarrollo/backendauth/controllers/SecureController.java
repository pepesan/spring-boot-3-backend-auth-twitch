package com.cursosdedesarrollo.backendauth.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/secure")
public class SecureController {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @GetMapping("/")
    public ResponseEntity<String> secureEndpoint(@RequestHeader("Authorization") String authorizationHeader) {
        return getStringResponseEntity(authorizationHeader, "/");

    }

    /*
        Este método permite una vez dentro de la url segura descifrar el token (otra vez) para capturar información
        Lo suyo sería meter el token resuelto en sesión de la app
     */
    private ResponseEntity<String> getStringResponseEntity(String authorizationHeader, String path) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Obtener el token sin la parte "Bearer "
            String token = authorizationHeader.substring(7);

            // Parsear el token y obtener los datos
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret) // Reemplaza "secretKey" con la clave secreta usada para firmar el token
                    .parseClaimsJws(token)
                    .getBody();

            // Extraer los datos del token que necesitas
            String username = claims.getSubject();
            // También puedes obtener otras propiedades personalizadas que hayas agregado al token
            // Ejemplo: String role = (String) claims.get("role");

            // Realizar las operaciones necesarias con los datos del token
            return ResponseEntity.ok("Hola, " + username + "! Esta es una ruta protegida por JWT "+path+".");
        }

        // Si no se proporciona un token válido
        return ResponseEntity.status(500).build();
    }

    @GetMapping("/admin/")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> secureAdminEndpoint(@RequestHeader("Authorization") String authorizationHeader) {
        return getStringResponseEntity(authorizationHeader, "/admin");
    }
}
