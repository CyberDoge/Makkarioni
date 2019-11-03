package com.example.Makkaroni.repository;

import com.example.Makkaroni.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUsername(String username);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserById(String username);
}
