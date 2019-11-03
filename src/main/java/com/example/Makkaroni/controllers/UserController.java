package com.example.Makkaroni.controllers;

import com.example.Makkaroni.models.User;
import com.example.Makkaroni.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final HttpSession httpSession;
    public UserController(UserRepository userRepository, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.httpSession = httpSession;
    }

    @PostMapping(path = {"/api/users/", "/register"})
    public ResponseEntity registerUser(@RequestBody User user) {
        if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("username already taken");
        }
        return ResponseEntity.ok(userRepository.insert(user));
    }

    @PostMapping(path = {"/login"})
    public ResponseEntity login(@RequestHeader("username") String username) {
        return userRepository.findUserByUsername(username).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/api/users/{username}")
    public ResponseEntity getUserByUsername(@PathVariable("username") String username) {
        return userRepository.findUserByUsername(username)
                .map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
