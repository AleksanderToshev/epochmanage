package com.alex.epochmanage.repository;

import com.alex.epochmanage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);


    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);
}
