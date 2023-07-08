package com.cursosdedesarrollo.backendauth.services;

import com.cursosdedesarrollo.backendauth.domain.User;
import com.cursosdedesarrollo.backendauth.repositories.UsersRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
public class UserService {
    private UsersRepository usersRepository;

    @Autowired
    UserService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public List<User> findAll(){
        return (List<User>) this.usersRepository.findAll();
    }

    public User save(User user) {
        User storedUser = this.usersRepository.save(user);
        return storedUser;
    }

    public Optional<User> findById(Long id) {
        return this.usersRepository.findById(id);
    }

    public User deleleById(Long id) {
        Optional<User> usuarioGuardado = this.usersRepository.findById(id);
        if (usuarioGuardado.isPresent()){
            this.usersRepository.deleteById(id);
            return usuarioGuardado.get();
        }else {
            return new User();
        }

    }
}
