package com.cursosdedesarrollo.backendauth.services;

import com.cursosdedesarrollo.backendauth.domain.User;
import com.cursosdedesarrollo.backendauth.repositories.UsersRepository;
import com.cursosdedesarrollo.backendauth.security.services.UserDetailsImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
public class UserService implements UserDetailsService {
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

    public Optional<User> findByUsername(String username) {
        return this.usersRepository.findByUsername(username);
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }
}
