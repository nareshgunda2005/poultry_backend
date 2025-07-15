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
@CrossOrigin(origins = "https://poultry-c9ccc.web.app")
public class ExpensesController {

    @Autowired
    private ExpensesRepository expensesRepository;
    @Autowired
    private VaccinationExpensesRepository vaccinationExpensesRepository;
    @Autowired
    private MedicineExpensesRepository medicineExpensesRepository;
    @Autowired
    private ShedExpensesRepository shedExpensesRepository;
    @Autowired
    private OtherExpensesRepository otherExpensesRepository;

    @PostMapping("/food")
    public ResponseEntity<Expenses> saveFoodExpenses(@RequestBody Expenses expenses) {
        if (expenses.getEmail() == null || expenses.getFoodName() == null || 
            expenses.getQuantity() == null || expenses.getUnit() == null || expenses.getCost() == null) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "All fields (email, foodName, quantity, unit, cost) are required.")
                .build();
        }
        if (!expenses.getUnit().equals("kgs") && !expenses.getUnit().equals("tuns")) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "Unit must be 'kgs' or 'tuns'.")
                .build();
        }
        if (expenses.getQuantity() <= 0 || expenses.getCost() < 0) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "Quantity must be positive and cost cannot be negative.")
                .build();
        }
        Expenses saved = expensesRepository.save(expenses);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/vaccination")
    public ResponseEntity<VaccinationExpenses> saveVaccinationExpenses(@RequestBody VaccinationExpenses expenses) {
        if (expenses.getEmail() == null || expenses.getVaccinationFor() == null || expenses.getCost() == null) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "All fields (email, vaccinationFor, cost) are required.")
                .build();
        }
        if (expenses.getCost() < 0) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "Cost cannot be negative.")
                .build();
        }
        VaccinationExpenses saved = vaccinationExpensesRepository.save(expenses);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/medicine")
    public ResponseEntity<MedicineExpenses> saveMedicineExpenses(@RequestBody MedicineExpenses expenses) {
        if (expenses.getEmail() == null || expenses.getMedicineName() == null || expenses.getCost() == null) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "All fields (email, medicineName, cost) are required.")
                .build();
        }
        if (expenses.getCost() < 0) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "Cost cannot be negative.")
                .build();
        }
        MedicineExpenses saved = medicineExpensesRepository.save(expenses);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/shed")
    public ResponseEntity<ShedExpenses> saveShedExpenses(@RequestBody ShedExpenses expenses) {
        if (expenses.getEmail() == null || expenses.getShedDescription() == null || expenses.getCost() == null) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "All fields (email, shedDescription, cost) are required.")
                .build();
        }
        if (expenses.getCost() < 0) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "Cost cannot be negative.")
                .build();
        }
        ShedExpenses saved = shedExpensesRepository.save(expenses);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/other")
    public ResponseEntity<OtherExpenses> saveOtherExpenses(@RequestBody OtherExpenses expenses) {
        if (expenses.getEmail() == null || expenses.getDescription() == null || expenses.getCost() == null) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "All fields (email, description, cost) are required.")
                .build();
        }
        if (expenses.getCost() < 0) {
            return ResponseEntity.badRequest()
                .header("Error-Message", "Cost cannot be negative.")
                .build();
        }
        OtherExpenses saved = otherExpensesRepository.save(expenses);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Object>> searchExpensesByDate(@RequestParam String date, @RequestParam String email) {
        List<Object> allExpenses = new ArrayList<>();
        allExpenses.addAll(expensesRepository.findByEmailAndDate(email, date));
        allExpenses.addAll(vaccinationExpensesRepository.findByEmailAndDate(email, date));
        allExpenses.addAll(medicineExpensesRepository.findByEmailAndDate(email, date));
        allExpenses.addAll(shedExpensesRepository.findByEmailAndDate(email, date));
        allExpenses.addAll(otherExpensesRepository.findByEmailAndDate(email, date));
        return ResponseEntity.ok(allExpenses);
    }

    @GetMapping("/food")
    public ResponseEntity<List<Expenses>> getAllFoodExpenses(@RequestParam String email) {
        return ResponseEntity.ok(expensesRepository.findByEmail(email));
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
        expensesRepository.deleteByEmail(email);
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