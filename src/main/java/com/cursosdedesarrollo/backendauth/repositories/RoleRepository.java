package com.cursosdedesarrollo.backendauth.repositories;

import com.cursosdedesarrollo.backendauth.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
