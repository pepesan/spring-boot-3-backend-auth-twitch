package com.cursosdedesarrollo.backendauth.domain;

import com.cursosdedesarrollo.backendauth.dto.UserRegisterDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AuthUsers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String type;
    private Boolean active;
    private String activationToken;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "auth_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

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
