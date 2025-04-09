package com.example.controller;

import com.example.model.OutersSales;
import com.example.model.DealersSales;
import com.example.repository.OutersSalesRepository;
import com.example.repository.DealersSalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "http://localhost:3000")
public class SalesController {

    @Autowired
    private OutersSalesRepository outersSalesRepository;

    @Autowired
    private DealersSalesRepository dealersSalesRepository;

    @PostMapping("/outers")
    public ResponseEntity<OutersSales> saveOutersSales(@RequestBody OutersSales sales) {
        if (sales.getDate() == null || sales.getQuantity() <= 0 || sales.getAmount() <= 0) {
            return ResponseEntity.badRequest().build();
        }
        OutersSales saved = outersSalesRepository.save(sales);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/dealers")
    public ResponseEntity<DealersSales> saveDealersSales(@RequestBody DealersSales sales) {
        if (sales.getDate() == null || sales.getQuantity() <= 0 || sales.getAmount() <= 0) {
            return ResponseEntity.badRequest().build();
        }
        DealersSales saved = dealersSalesRepository.save(sales);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/outers/check-date")
    public ResponseEntity<Map<String, Object>> checkOutersDate(@RequestParam String date) {
        if (date == null || date.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(createErrorResponse("Date parameter is required"));
        }
        List<OutersSales> sales = outersSalesRepository.findAll()
            .stream()
            .filter(s -> s.getDate().equals(date))
            .toList();
        
        Map<String, Object> response = new HashMap<>();
        response.put("exists", !sales.isEmpty());
        response.put("data", sales);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dealers/check-date")
    public ResponseEntity<Map<String, Object>> checkDealersDate(@RequestParam String date) {
        if (date == null || date.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(createErrorResponse("Date parameter is required"));
        }
        List<DealersSales> sales = dealersSalesRepository.findAll()
            .stream()
            .filter(s -> s.getDate().equals(date))
            .toList();
        
        Map<String, Object> response = new HashMap<>();
        response.put("exists", !sales.isEmpty());
        response.put("data", sales);
        return ResponseEntity.ok(response);
    }

    // New endpoint to fetch all OutersSales
    @GetMapping("/outers")
    public ResponseEntity<List<OutersSales>> getAllOutersSales() {
        List<OutersSales> sales = outersSalesRepository.findAll();
        return ResponseEntity.ok(sales);
    }

    // New endpoint to fetch all DealersSales
    @GetMapping("/dealers")
    public ResponseEntity<List<DealersSales>> getAllDealersSales() {
        List<DealersSales> sales = dealersSalesRepository.findAll();
        return ResponseEntity.ok(sales);
    }

    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("exists", false);
        response.put("error", message);
        return response;
    }
}