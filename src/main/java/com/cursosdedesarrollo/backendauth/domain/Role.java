package com.cursosdedesarrollo.backendauth.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AuthRoles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

}
