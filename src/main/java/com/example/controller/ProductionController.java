package com.example.controller;

import com.example.model.Production;
import com.example.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

    @PostMapping
    public ResponseEntity<Production> saveProduction(@RequestBody Production production) {
        Production savedProduction = productionRepository.save(production);
        return ResponseEntity.ok(savedProduction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Production> updateProduction(@PathVariable Long id, @RequestBody Production production) {
        Production existingProduction = productionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Production entry not found with id: " + id));
        existingProduction.setDate(production.getDate());
        existingProduction.setTrays(production.getTrays());
        existingProduction.setEggs(production.getEggs());
        existingProduction.setBoxes(production.getBoxes());
        existingProduction.setCost(production.getCost());
        existingProduction.setMortality(production.getMortality());
        existingProduction.setFeeds(production.getFeeds());
        existingProduction.setDoubleEggs(production.getDoubleEggs());
        existingProduction.setDamagedEggs(production.getDamagedEggs());
        existingProduction.setWorkerPresence(production.getWorkerPresence());
        existingProduction.setProductionPercentage(production.getProductionPercentage()); // New field
        existingProduction.setEmail(production.getEmail());
        Production updatedProduction = productionRepository.save(existingProduction);
        return ResponseEntity.ok(updatedProduction);
    }

    @GetMapping
    public ResponseEntity<List<Production>> getProductionByEmail(@RequestParam String email) {
        List<Production> productions = productionRepository.findByEmail(email);
        return ResponseEntity.ok(productions);
    }

    @GetMapping("/check-date")
    public ResponseEntity<Map<String, Object>> checkDate(@RequestParam String date, @RequestParam String email) {
        Production production = productionRepository.findByDateAndEmail(date, email)
                .orElse(null);
        Map<String, Object> response = new HashMap<>();
        response.put("exists", production != null);
        response.put("data", production);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/by-email")
    @Transactional
    public ResponseEntity<Void> deleteProductionByEmail(@RequestParam String email) {
        productionRepository.deleteByEmail(email);
        return ResponseEntity.ok().build();
    }
}