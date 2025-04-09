package com.example.controller;

import com.example.model.*;
import com.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "http://localhost:3000")
public class ExpensesController {

    @Autowired
    private FoodExpensesRepository foodExpensesRepository;
    @Autowired
    private VaccinationExpensesRepository vaccinationExpensesRepository;
    @Autowired
    private MedicineExpensesRepository medicineExpensesRepository;
    @Autowired
    private ShedExpensesRepository shedExpensesRepository;
    @Autowired
    private OtherExpensesRepository otherExpensesRepository;

    @PostMapping("/food")
    public ResponseEntity<FoodExpenses> saveFoodExpenses(@RequestBody FoodExpenses expenses) {
        FoodExpenses saved = foodExpensesRepository.save(expenses);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/vaccination")
    public ResponseEntity<VaccinationExpenses> saveVaccinationExpenses(@RequestBody VaccinationExpenses expenses) {
        VaccinationExpenses saved = vaccinationExpensesRepository.save(expenses);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/medicine")
    public ResponseEntity<MedicineExpenses> saveMedicineExpenses(@RequestBody MedicineExpenses expenses) {
        MedicineExpenses saved = medicineExpensesRepository.save(expenses);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/shed")
    public ResponseEntity<ShedExpenses> saveShedExpenses(@RequestBody ShedExpenses expenses) {
        ShedExpenses saved = shedExpensesRepository.save(expenses);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/other")
    public ResponseEntity<OtherExpenses> saveOtherExpenses(@RequestBody OtherExpenses expenses) {
        OtherExpenses saved = otherExpensesRepository.save(expenses);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Object>> searchExpensesByDate(@RequestParam String date) {
        List<Object> allExpenses = new ArrayList<>();
        allExpenses.addAll(foodExpensesRepository.findAll().stream()
                .filter(e -> e.getDate().equals(date)).toList());
        allExpenses.addAll(vaccinationExpensesRepository.findAll().stream()
                .filter(e -> e.getDate().equals(date)).toList());
        allExpenses.addAll(medicineExpensesRepository.findAll().stream()
                .filter(e -> e.getDate().equals(date)).toList());
        allExpenses.addAll(shedExpensesRepository.findAll().stream()
                .filter(e -> e.getDate().equals(date)).toList());
        allExpenses.addAll(otherExpensesRepository.findAll().stream()
                .filter(e -> e.getDate().equals(date)).toList());
        return ResponseEntity.ok(allExpenses);
    }
}