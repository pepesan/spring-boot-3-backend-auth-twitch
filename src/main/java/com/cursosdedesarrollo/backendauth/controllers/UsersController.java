package com.cursosdedesarrollo.backendauth.controllers;

import com.cursosdedesarrollo.backendauth.domain.Role;
import com.cursosdedesarrollo.backendauth.domain.User;
import com.cursosdedesarrollo.backendauth.services.RolesService;
import com.cursosdedesarrollo.backendauth.services.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
@Getter
@Setter
public class UsersController {

    private UserService userService;
    private RolesService rolesService;

    @Autowired
    UsersController(UserService userService, RolesService rolesService){
        this.userService = userService;
        this.rolesService = rolesService;
    }
    @GetMapping("/")
    public List<User> index(){
        return this.userService.findAll();
    }
    @PostMapping("/")
    public User create(@RequestBody @Valid User user){
        return this.userService.save(user);
    }

    @GetMapping("/{id}/role")
    public Set<Role> getUserRoles(@PathVariable("id")@Valid Long id){
        Optional<User> posibleUser = this.userService.findById(id);
        User userDevuelto = new User();
        if (posibleUser.isPresent()){
            // caso de que hay usuario
            userDevuelto = posibleUser.get();
        }
        return userDevuelto.getRoles();
    }
    @GetMapping("/{userID}/role/{roleID}")
    public Set<Role> setUserRole(
            @PathVariable("userID")@Valid Long userID,
            @PathVariable("roleID")@Valid Long roleID
    ){
        Optional<User> posibleUser = this.userService.findById(userID);
        Optional<Role> posibleRole = this.rolesService.findById(roleID);
        User userDevuelto = new User();
        Role roleDevuelto = new Role();
        if (posibleUser.isPresent() && posibleRole.isPresent()){
            // caso de que hay usuario
            userDevuelto = posibleUser.get();
            roleDevuelto = posibleRole.get();
            userDevuelto.getRoles().add(roleDevuelto);
            this.userService.save(userDevuelto);
        }
        return userDevuelto.getRoles();
    }
    @GetMapping("/{id}")
    public User getById(@PathVariable("id")@Valid Long id){
        Optional<User> posibleUser = this.userService.findById(id);
        User userDevuelto = new User();
        if (posibleUser.isPresent()){
            // caso de que hay usuario
            userDevuelto = posibleUser.get();
        }
        return userDevuelto;
    }

    @PutMapping("/{id}")
    public User modifyById(
            @PathVariable("id")@Valid Long id,
            @RequestBody @Valid User user
    ){
        Optional<User> posibleUser = this.userService.findById(id);
        User userDevuelto = new User();
        if (posibleUser.isPresent()){
            // caso de que hay usuario
            user.setId(id);
            userDevuelto = this.userService.save(user);
        }
        return userDevuelto;
    }
    @DeleteMapping("/{id}")
    public User deleteById(@PathVariable("id")@Valid Long id){
        Optional<User> posibleUser = this.userService.findById(id);
        User userDevuelto = new User();
        if (posibleUser.isPresent()){
            // caso de que hay usuario
            this.userService.deleleById(id);
            userDevuelto = posibleUser.get();
        }
        return userDevuelto;
    }
}
