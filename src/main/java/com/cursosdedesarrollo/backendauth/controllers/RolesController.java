package com.cursosdedesarrollo.backendauth.controllers;

import com.cursosdedesarrollo.backendauth.domain.Role;
import com.cursosdedesarrollo.backendauth.domain.User;
import com.cursosdedesarrollo.backendauth.repositories.RolesRepository;
import com.cursosdedesarrollo.backendauth.services.RolesService;
import com.cursosdedesarrollo.backendauth.services.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/roles")
@Getter
@Setter
public class RolesController {

    private RolesService rolesService;

    @Autowired
    RolesController(RolesService rolesService){
        this.rolesService = rolesService;
    }
    @GetMapping("/")
    public List<Role> index(){
        return this.rolesService.findAll();
    }
    @PostMapping("/")
    public Role create(@RequestBody @Valid Role role){
        return this.rolesService.save(role);
    }
    @GetMapping("/{id}")
    public Role getById(@PathVariable("id")@Valid Long id){
        Optional<Role> posibleRole = this.rolesService.findById(id);
        Role roleDevuelto = new Role();
        if (posibleRole.isPresent()){
            // caso de que hay usuario
            roleDevuelto = posibleRole.get();
        }
        return roleDevuelto;
    }

    @PutMapping("/{id}")
    public Role modifyById(
            @PathVariable("id")@Valid Long id,
            @RequestBody @Valid Role role
    ){
        Optional<Role> posibleRole = this.rolesService.findById(id);
        Role roleDevuelto = new Role();
        if (posibleRole.isPresent()){
            // caso de que hay usuario
            role.setId(id);
            roleDevuelto = this.rolesService.save(role);
        }
        return roleDevuelto;
    }
    @DeleteMapping("/{id}")
    public Role deleteById(@PathVariable("id")@Valid Long id){
        Optional<Role> posibleRole = this.rolesService.findById(id);
        Role roleDevuelto = new Role();
        if (posibleRole.isPresent()){
            // caso de que hay usuario
            this.rolesService.deleleById(id);
            roleDevuelto = posibleRole.get();
        }
        return roleDevuelto;
    }
}
