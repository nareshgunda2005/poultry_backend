package com.example.controller;

import com.example.model.OutersSales;
import com.example.model.DealersSales;
import com.example.repository.OutersSalesRepository;
import com.example.repository.DealersSalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "https://poultry-c9ccc.web.app")
public class SalesController {

    @Autowired
    private OutersSalesRepository outersSalesRepository;

    @Autowired
    private DealersSalesRepository dealersSalesRepository;

    @PostMapping("/outers")
    public ResponseEntity<OutersSales> saveOutersSales(@RequestBody OutersSales sales) {
        if (sales.getDate() == null || sales.getQuantity() <= 0 || sales.getAmount() <= 0 || sales.getEmail() == null) {
            return ResponseEntity.badRequest().build();
        }
        OutersSales saved = outersSalesRepository.save(sales);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/dealers")
    public ResponseEntity<DealersSales> saveDealersSales(@RequestBody DealersSales sales) {
        if (sales.getDate() == null || sales.getQuantity() <= 0 || sales.getAmount() <= 0 || sales.getEmail() == null) {
            return ResponseEntity.badRequest().build();
        }
        DealersSales saved = dealersSalesRepository.save(sales);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/outers/check-date")
    public ResponseEntity<Map<String, Object>> checkOutersDate(@RequestParam String date, @RequestParam String email) {
        if (date == null || date.trim().isEmpty() || email == null) {
            return ResponseEntity.badRequest().body(createErrorResponse("Date and email parameters are required"));
        }
        List<OutersSales> sales = outersSalesRepository.findByEmail(email)
            .stream()
            .filter(s -> s.getDate().equals(date))
            .toList();
        
        Map<String, Object> response = new HashMap<>();
        response.put("exists", !sales.isEmpty());
        response.put("data", sales);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dealers/check-date")
    public ResponseEntity<Map<String, Object>> checkDealersDate(@RequestParam String date, @RequestParam String email) {
        if (date == null || date.trim().isEmpty() || email == null) {
            return ResponseEntity.badRequest().body(createErrorResponse("Date and email parameters are required"));
        }
        List<DealersSales> sales = dealersSalesRepository.findByEmail(email)
            .stream()
            .filter(s -> s.getDate().equals(date))
            .toList();
        
        Map<String, Object> response = new HashMap<>();
        response.put("exists", !sales.isEmpty());
        response.put("data", sales);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/outers")
    public ResponseEntity<List<OutersSales>> getAllOutersSales(@RequestParam String email) {
        List<OutersSales> sales = outersSalesRepository.findByEmail(email);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/dealers")
    public ResponseEntity<List<DealersSales>> getAllDealersSales(@RequestParam String email) {
        List<DealersSales> sales = dealersSalesRepository.findByEmail(email);
        return ResponseEntity.ok(sales);
    }

    @DeleteMapping("/outers/by-email")
    @Transactional
    public ResponseEntity<Void> deleteOutersSalesByEmail(@RequestParam String email) {
        outersSalesRepository.deleteByEmail(email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/dealers/by-email")
    @Transactional
    public ResponseEntity<Void> deleteDealersSalesByEmail(@RequestParam String email) {
        dealersSalesRepository.deleteByEmail(email);
        return ResponseEntity.ok().build();
    }

    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("exists", false);
        response.put("error", message);
        return response;
    }
}