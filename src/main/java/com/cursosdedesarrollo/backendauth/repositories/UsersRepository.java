package com.cursosdedesarrollo.backendauth.repositories;

import com.cursosdedesarrollo.backendauth.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Long > {
    public Optional<User> findByUsername(String username);
    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);
}
