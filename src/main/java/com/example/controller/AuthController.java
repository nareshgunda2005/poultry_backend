package com.example.controller;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "https://poultry-c9ccc.web.app")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    private static final String UPLOAD_DIR = "Uploads/";

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Backend is running");
    }

    @PostMapping(value = "/signup", consumes = {"multipart/form-data"})
    public ResponseEntity<Map<String, String>> signup(
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("username") String username,
            @RequestPart("poultryName") String poultryName, // Added poultryName
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage) {
        try {
            // Check if user already exists
            User existingUser = userRepository.findByEmail(email);
            if (existingUser != null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "User with this email already exists");
                return ResponseEntity.badRequest().body(response);
            }

            // Handle image upload
            String imagePath = null;
            if (profileImage != null && !profileImage.isEmpty()) {
                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                String originalFilename = profileImage.getOriginalFilename();
                String extension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : ".jpg";
                String fileName = email + "-" + System.currentTimeMillis() + extension;
                Path filePath = Paths.get(UPLOAD_DIR, fileName);
                Files.write(filePath, profileImage.getBytes());
                imagePath = "/Uploads/" + fileName;
            }

            // Create and save the user
            User user = new User();
            user.setEmail(email);
            user.setPassword(password); // Plain text
            user.setUsername(username);
            user.setPoultryName(poultryName); // Set poultryName
            user.setProfileImage(imagePath);

            userRepository.save(user);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Signup successful");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error during signup: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<User> signin(@RequestBody User user) {
        User dbUser = userRepository.findByEmail(user.getEmail());
        if (dbUser != null && user.getPassword().equals(dbUser.getPassword())) {
            return ResponseEntity.ok(dbUser); // Returns user with poultryName
        }
        return ResponseEntity.status(401).body(null);
    }

    @PostMapping("/verify-password")
    public ResponseEntity<Map<String, Boolean>> verifyPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        User dbUser = userRepository.findByEmail(email);
        Map<String, Boolean> response = new HashMap<>();
        if (dbUser != null && password.equals(dbUser.getPassword())) {
            response.put("verified", true);
        } else {
            response.put("verified", false);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user); // Returns user with poultryName
    }

    @PutMapping(value = "/update-password", consumes = {"multipart/form-data"})
    public ResponseEntity<Map<String, String>> updatePassword(
            @RequestPart("email") String email,
            @RequestPart("password") String newPassword) {
        try {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "User not found with email: " + email);
                return ResponseEntity.badRequest().body(response);
            }

            user.setPassword(newPassword);
            userRepository.save(user);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Password updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error updating password: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping(value = "/update-profile-image", consumes = {"multipart/form-data"})
    public ResponseEntity<Map<String, String>> updateProfileImage(
            @RequestPart("email") String email,
            @RequestPart("profileImage") MultipartFile profileImage) {
        try {
            if (profileImage == null || profileImage.isEmpty()) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "No file uploaded");
                return ResponseEntity.badRequest().body(response);
            }

            User user = userRepository.findByEmail(email);
            if (user == null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "User not found with email: " + email);
                return ResponseEntity.badRequest().body(response);
            }

            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String originalFilename = profileImage.getOriginalFilename();
            String extension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : ".jpg";
            String fileName = email + "-" + System.currentTimeMillis() + extension;
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            Files.write(filePath, profileImage.getBytes());
            String imagePath = "/Uploads/" + fileName;

            if (user.getProfileImage() != null) {
                File oldFile = new File(user.getProfileImage().replace("/Uploads/", UPLOAD_DIR));
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }

            user.setProfileImage(imagePath);
            userRepository.save(user);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Profile image updated successfully");
            response.put("profileImage", imagePath);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error updating profile image: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}