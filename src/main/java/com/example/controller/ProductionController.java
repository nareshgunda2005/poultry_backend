package com.example.controller;

import com.example.model.Production;
import com.example.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/production")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductionController {
    @Autowired
    private ProductionRepository productionRepository;

    @GetMapping("/bird-count")
    public ResponseEntity<Integer> getBirdCount() {
        List<Production> productions = productionRepository.findAll();
        int totalMortality = productions.stream().mapToInt(Production::getMortality).sum();
        int initialBirdCount = 10000; // Default starting value
        return ResponseEntity.ok(initialBirdCount - totalMortality);
    }

    @GetMapping
    public ResponseEntity<List<Production>> getAllProductions() {
        return ResponseEntity.ok(productionRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Production> saveProduction(@RequestBody Production production) {
        Production savedProduction = productionRepository.save(production);
        return ResponseEntity.ok(savedProduction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Production> updateProduction(@PathVariable Long id, @RequestBody Production production) {
        Production existing = productionRepository.findById(id).orElse(null);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setDate(production.getDate());
        existing.setEggs(production.getEggs());
        existing.setBoxes(production.getBoxes());
        existing.setCost(production.getCost());
        existing.setMortality(production.getMortality());
        existing.setFeeds(production.getFeeds());
        Production updated = productionRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/check-date")
    public ResponseEntity<Map<String, Object>> checkDate(@RequestParam String date) {
        Production existing = productionRepository.findByDate(date);
        Map<String, Object> response = new HashMap<>();
        response.put("exists", existing != null);
        if (existing != null) {
            response.put("data", existing);
        }
        return ResponseEntity.ok(response);
    }
}