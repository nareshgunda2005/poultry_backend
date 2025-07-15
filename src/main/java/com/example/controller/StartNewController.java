package com.example.controller;

import com.example.model.StartNew;
import com.example.repository.StartNewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/startnew")
@CrossOrigin(origins = "https://poultry-c9ccc.web.app")
public class StartNewController {

    @Autowired
    private StartNewRepository startNewRepository;

    @PostMapping
    public ResponseEntity<StartNew> createStartNew(@RequestBody StartNew startNew) {
        StartNew savedStartNew = startNewRepository.save(startNew);
        return ResponseEntity.ok(savedStartNew);
    }

    @GetMapping("/by-email")
    public ResponseEntity<StartNew> getStartNewByEmail(@RequestParam String email) {
        StartNew startNew = startNewRepository.findByEmail(email)
                .orElse(new StartNew());
        return ResponseEntity.ok(startNew);
    }

    @DeleteMapping("/by-email")
    @Transactional
    public ResponseEntity<Void> deleteStartNewByEmail(@RequestParam String email) {
        startNewRepository.deleteByEmail(email);
        return ResponseEntity.ok().build();
    }
}