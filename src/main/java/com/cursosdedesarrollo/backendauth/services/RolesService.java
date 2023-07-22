package com.cursosdedesarrollo.backendauth.services;

import com.cursosdedesarrollo.backendauth.domain.Role;
import com.cursosdedesarrollo.backendauth.repositories.RolesRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
public class RolesService {
    private RolesRepository rolesRepository;

    @Autowired
    RolesService(RolesRepository rolesRepository){
        this.rolesRepository= rolesRepository;
    }

    public List<Role> findAll(){
        return (List<Role>) this.rolesRepository.findAll();
    }

    public Role save(Role role) {
        Role storedRole = this.rolesRepository.save(role);
        return storedRole;
    }

    public Optional<Role> findById(Long id) {
        return this.rolesRepository.findById(id);
    }

    public Optional<Role> findByName(String role) {
        return this.rolesRepository.findByName(role);
    }

    public Role deleleById(Long id) {
        Optional<Role> roleGuardado = this.rolesRepository.findById(id);
        if (roleGuardado.isPresent()){
            this.rolesRepository.deleteById(id);
            return roleGuardado.get();
        }else {
            return new Role();
        }

    }
}
