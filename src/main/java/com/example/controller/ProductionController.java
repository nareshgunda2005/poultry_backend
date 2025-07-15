package com.example.controller;

import com.example.model.Production;
import com.example.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/production")
@CrossOrigin(origins = "https://poultry-c9ccc.web.app")
public class ProductionController {

    @Autowired
    private ProductionRepository productionRepository;

    @PostMapping
    public ResponseEntity<Production> saveProduction(@RequestBody Production production) {
        if (production.getEmail() == null || production.getDate() == null || 
            production.getTrays() == null || production.getEggs() == null || 
            production.getBoxes() == null || production.getCost() == null || 
            production.getMortality() == null || production.getFeeds() == null || 
            production.getWorkerPresence() == null) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "All required fields (email, date, trays, eggs, boxes, cost, mortality, feeds, workerPresence) must be provided.")
                .build();
        }
        if (production.getEggs() < 0 || production.getBoxes() < 0 || production.getCost() < 0 || 
            production.getMortality() < 0 || production.getFeeds() < 0) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "Numeric fields cannot be negative.")
                .build();
        }
        if (production.getDoubleEggs() != null && production.getDoubleEggs() > production.getEggs()) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "Double eggs cannot exceed total eggs.")
                .build();
        }
        if (production.getDamagedEggs() != null && production.getDamagedEggs() > production.getEggs()) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "Damaged eggs cannot exceed total eggs.")
                .build();
        }
        Production saved = productionRepository.save(production);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Production>> getAllProduction(@RequestParam String email) {
        return ResponseEntity.ok(productionRepository.findByEmail(email));
    }

    @GetMapping("/check-date")
    public ResponseEntity<Map<String, Object>> checkDate(@RequestParam String date, @RequestParam String email) {
        Optional<Production> production = productionRepository.findByEmailAndDate(email, date);
        Map<String, Object> response = new HashMap<>();
        if (production.isPresent()) {
            response.put("exists", true);
            response.put("data", production.get());
        } else {
            response.put("exists", false);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/range")
    public ResponseEntity<List<Production>> getProductionByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String email) {
        try {
            // Parse and validate dates
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);

            if (start.isAfter(end)) {
                return ResponseEntity.badRequest()
                    .header("Error-Message", "Start date must be before or equal to end date.")
                    .build();
            }

            // Fetch production data within the date range
            List<Production> productions = productionRepository.findByEmailAndDateBetween(email, startDate, endDate);
            return ResponseEntity.ok(productions);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "Invalid date format. Use yyyy-MM-dd.")
                .build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "Error fetching production data: " + e.getMessage())
                .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Production> updateProduction(@PathVariable Long id, @RequestBody Production production) {
        if (!productionRepository.existsById(id)) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "Production record with ID " + id + " not found.")
                .build();
        }
        if (production.getEmail() == null || production.getDate() == null || 
            production.getTrays() == null || production.getEggs() == null || 
            production.getBoxes() == null || production.getCost() == null || 
            production.getMortality() == null || production.getFeeds() == null || 
            production.getWorkerPresence() == null) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "All required fields (email, date, trays, eggs, boxes, cost, mortality, feeds, workerPresence) must be provided.")
                .build();
        }
        if (production.getEggs() < 0 || production.getBoxes() < 0 || production.getCost() < 0 || 
            production.getMortality() < 0 || production.getFeeds() < 0) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "Numeric fields cannot be negative.")
                .build();
        }
        if (production.getDoubleEggs() != null && production.getDoubleEggs() > production.getEggs()) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "Double eggs cannot exceed total eggs.")
                .build();
        }
        if (production.getDamagedEggs() != null && production.getDamagedEggs() > production.getEggs()) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "Damaged eggs cannot exceed total eggs.")
                .build();
        }
        production.setId(id);
        Production updated = productionRepository.save(production);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/by-email")
    @Transactional
    public ResponseEntity<Void> deleteProductionByEmail(@RequestParam String email) {
        productionRepository.deleteByEmail(email);
        return ResponseEntity.ok().build();
    }
}