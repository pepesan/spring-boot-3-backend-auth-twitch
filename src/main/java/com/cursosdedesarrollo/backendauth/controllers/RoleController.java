package com.cursosdedesarrollo.backendauth.controllers;

import com.cursosdedesarrollo.backendauth.domain.Role;
import com.cursosdedesarrollo.backendauth.services.RoleService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/roles")
@Getter
@Setter
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);


    private RoleService roleService;

    @Autowired
    RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping("/")
    public List<Role> getRoles(){
        return this.roleService.findAll();
    }

    @PostMapping("/")
    public Role create(@RequestBody @Valid Role role){
        logger.info("Role: {}"+ role);
        return this.roleService.save(role);
    }

    @GetMapping("/{id}")
    public Role getById(@PathVariable("id") @Valid Long id){
        Optional<Role> posibleRole = this.roleService.findById(id);
        Role roleDevuelto = new Role();
        if(posibleRole.isPresent()){
            roleDevuelto = posibleRole.get();
        }
        return roleDevuelto;
    }
    @PutMapping("/{id}")
    public Role modifyById(
            @PathVariable("id") @Valid Long id,
            @RequestBody @Valid Role role
    ){
        Optional<Role> posibleRole = this.roleService.findById(id);
        Role roleDevuelto = new Role();
        if(posibleRole.isPresent()){
            roleDevuelto = posibleRole.get();
            roleDevuelto.setName(role.getName());
            roleDevuelto = this.roleService.save(roleDevuelto);
        }
        return roleDevuelto;
    }

    @DeleteMapping("/{id}")
    public Role deleteById(@PathVariable("id") @Valid Long id){
        Optional<Role> posibleRole = this.roleService.findById(id);
        Role role = new Role();
        if (posibleRole.isPresent()){
            this.roleService.deleteById(id);
            role = posibleRole.get();
        }
        return role;
    }
}
