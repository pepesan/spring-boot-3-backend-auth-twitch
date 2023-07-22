package com.cursosdedesarrollo.backendauth.services;

import com.cursosdedesarrollo.backendauth.domain.Role;
import com.cursosdedesarrollo.backendauth.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }


    public List<Role> findAll() {
        return (List<Role>)this.roleRepository.findAll();
    }

    public Role save(Role role) {
        Role storedRole = this.roleRepository.save(role);
        return storedRole;
    }

    public Optional<Role> findById(Long id) {
        return this.roleRepository.findById(id);
    }

    public void deleteById(Long id) {
        this.roleRepository.deleteById(id);
    }
}
