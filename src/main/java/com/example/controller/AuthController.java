package com.example.controller;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Backend is running");
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            return ResponseEntity.badRequest().body(null);
        }
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/signin")
    public ResponseEntity<User> signin(@RequestBody User user) {
        User dbUser = userRepository.findByEmail(user.getEmail());
        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok(dbUser);
        }
        return ResponseEntity.status(401).body(null);
    }

    @PostMapping("/verify-password")
    public ResponseEntity<Map<String, Boolean>> verifyPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        User dbUser = userRepository.findByEmail(email);
        Map<String, Boolean> response = new HashMap<>();
        
        if (dbUser != null && dbUser.getPassword().equals(password)) {
            response.put("verified", true);
        } else {
            response.put("verified", false);
        }
        return ResponseEntity.ok(response);
    }
}