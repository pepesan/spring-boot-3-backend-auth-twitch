package com.cursosdedesarrollo.backendauth.repositories;

import com.cursosdedesarrollo.backendauth.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Long > {
}
