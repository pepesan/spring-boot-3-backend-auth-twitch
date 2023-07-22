package com.cursosdedesarrollo.backendauth.repositories;

import com.cursosdedesarrollo.backendauth.domain.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RolesRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
