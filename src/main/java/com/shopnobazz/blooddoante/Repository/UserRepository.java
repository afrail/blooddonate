package com.shopnobazz.blooddoante.Repository;

import org.springframework.data.repository.CrudRepository;

import com.shopnobazz.blooddoante.domain.User;
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
    
}

