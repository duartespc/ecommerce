package com.example.ecommerce.controllers;

import com.example.ecommerce.domain.user.User;
import com.example.ecommerce.dto.InfoRequestDTO;
import com.example.ecommerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;

    @PostMapping("/user")
    public ResponseEntity<User> getUser(@RequestBody InfoRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (user != null) {
            return ResponseEntity.ok(user);

        }
        return ResponseEntity.badRequest().build();
    }
}
