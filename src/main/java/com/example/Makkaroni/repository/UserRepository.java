package com.example.Makkaroni.repository;

import com.example.Makkaroni.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUsername(String username);
    Optional<User> findUserByUsername(String username);
}
