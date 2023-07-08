package com.cursosdedesarrollo.backendauth.domain;

import com.cursosdedesarrollo.backendauth.dto.UserRegisterDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AuthUsers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String type;
    private Boolean active;
    private String activationToken;
    public User(UserRegisterDTO userRegister){
        this.username = userRegister.getUsername();
        this.password = userRegister.getPassword();
        this.email = userRegister.getEmail();
        this.type = "USER";
        this.active = false;
        this.activationToken = "";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
