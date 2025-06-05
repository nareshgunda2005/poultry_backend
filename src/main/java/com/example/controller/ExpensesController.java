package com.example.controller;

import com.example.model.*;
import com.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
        if (expenses.getEmail() == null) {
            return ResponseEntity.badRequest().build();
        }
        FoodExpenses saved = foodExpensesRepository.save(expenses);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/vaccination")
    public ResponseEntity<VaccinationExpenses> saveVaccinationExpenses(@RequestBody VaccinationExpenses expenses) {
        if (expenses.getEmail() == null) {
            return ResponseEntity.badRequest().build();
        }
        VaccinationExpenses saved = vaccinationExpensesRepository.save(expenses);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/medicine")
    public ResponseEntity<MedicineExpenses> saveMedicineExpenses(@RequestBody MedicineExpenses expenses) {
        if (expenses.getEmail() == null) {
            return ResponseEntity.badRequest().build();
        }
        MedicineExpenses saved = medicineExpensesRepository.save(expenses);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/shed")
    public ResponseEntity<ShedExpenses> saveShedExpenses(@RequestBody ShedExpenses expenses) {
        if (expenses.getEmail() == null) {
            return ResponseEntity.badRequest().build();
        }
        ShedExpenses saved = shedExpensesRepository.save(expenses);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/other")
    public ResponseEntity<OtherExpenses> saveOtherExpenses(@RequestBody OtherExpenses expenses) {
        if (expenses.getEmail() == null) {
            return ResponseEntity.badRequest().build();
        }
        OtherExpenses saved = otherExpensesRepository.save(expenses);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Object>> searchExpensesByDate(@RequestParam String date, @RequestParam String email) {
        List<Object> allExpenses = new ArrayList<>();
        allExpenses.addAll(foodExpensesRepository.findByEmail(email).stream()
                .filter(e -> e.getDate().equals(date)).toList());
        allExpenses.addAll(vaccinationExpensesRepository.findByEmail(email).stream()
                .filter(e -> e.getDate().equals(date)).toList());
        allExpenses.addAll(medicineExpensesRepository.findByEmail(email).stream()
                .filter(e -> e.getDate().equals(date)).toList());
        allExpenses.addAll(shedExpensesRepository.findByEmail(email).stream()
                .filter(e -> e.getDate().equals(date)).toList());
        allExpenses.addAll(otherExpensesRepository.findByEmail(email).stream()
                .filter(e -> e.getDate().equals(date)).toList());
        return ResponseEntity.ok(allExpenses);
    }

    @GetMapping("/food")
    public ResponseEntity<List<FoodExpenses>> getAllFoodExpenses(@RequestParam String email) {
        return ResponseEntity.ok(foodExpensesRepository.findByEmail(email));
    }

    @GetMapping("/vaccination")
    public ResponseEntity<List<VaccinationExpenses>> getAllVaccinationExpenses(@RequestParam String email) {
        return ResponseEntity.ok(vaccinationExpensesRepository.findByEmail(email));
    }

    @GetMapping("/medicine")
    public ResponseEntity<List<MedicineExpenses>> getAllMedicineExpenses(@RequestParam String email) {
        return ResponseEntity.ok(medicineExpensesRepository.findByEmail(email));
    }

    @GetMapping("/shed")
    public ResponseEntity<List<ShedExpenses>> getAllShedExpenses(@RequestParam String email) {
        return ResponseEntity.ok(shedExpensesRepository.findByEmail(email));
    }

    @GetMapping("/other")
    public ResponseEntity<List<OtherExpenses>> getAllOtherExpenses(@RequestParam String email) {
        return ResponseEntity.ok(otherExpensesRepository.findByEmail(email));
    }

    @DeleteMapping("/food/by-email")
    @Transactional
    public ResponseEntity<Void> deleteFoodExpensesByEmail(@RequestParam String email) {
        foodExpensesRepository.deleteByEmail(email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/vaccination/by-email")
    @Transactional
    public ResponseEntity<Void> deleteVaccinationExpensesByEmail(@RequestParam String email) {
        vaccinationExpensesRepository.deleteByEmail(email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/medicine/by-email")
    @Transactional
    public ResponseEntity<Void> deleteMedicineExpensesByEmail(@RequestParam String email) {
        medicineExpensesRepository.deleteByEmail(email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/shed/by-email")
    @Transactional
    public ResponseEntity<Void> deleteShedExpensesByEmail(@RequestParam String email) {
        shedExpensesRepository.deleteByEmail(email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/other/by-email")
    @Transactional
    public ResponseEntity<Void> deleteOtherExpensesByEmail(@RequestParam String email) {
        otherExpensesRepository.deleteByEmail(email);
        return ResponseEntity.ok().build();
    }
}