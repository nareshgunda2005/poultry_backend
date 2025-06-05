package com.example.controller;

import com.example.model.*;
import com.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/turnover")
@CrossOrigin(origins = "http://localhost:3000")
public class TurnOverController {

    @Autowired
    private OutersSalesRepository outersSalesRepository;

    @Autowired
    private DealersSalesRepository dealersSalesRepository;

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

    @GetMapping("/calculate")
    public ResponseEntity<Map<String, Double>> calculateTurnOver(
            @RequestParam String fromDate,
            @RequestParam String toDate,
            @RequestParam String email) {
        if (fromDate == null || toDate == null || email == null) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            // Fetch sales data within the date range for the given email
            List<OutersSales> outersSales = outersSalesRepository.findByEmail(email)
                    .stream()
                    .filter(s -> s.getDate().compareTo(fromDate) >= 0 && s.getDate().compareTo(toDate) <= 0)
                    .toList();

            List<DealersSales> dealersSales = dealersSalesRepository.findByEmail(email)
                    .stream()
                    .filter(s -> s.getDate().compareTo(fromDate) >= 0 && s.getDate().compareTo(toDate) <= 0)
                    .toList();

            // Calculate total sales
            double totalOutersSales = outersSales.stream().mapToDouble(OutersSales::getAmount).sum();
            double totalDealersSales = dealersSales.stream().mapToDouble(DealersSales::getAmount).sum();
            double totalSales = totalOutersSales + totalDealersSales;

            // Fetch expenses data within the date range for the given email
            List<FoodExpenses> foodExpenses = foodExpensesRepository.findByEmail(email)
                    .stream()
                    .filter(e -> e.getDate().compareTo(fromDate) >= 0 && e.getDate().compareTo(toDate) <= 0)
                    .toList();

            List<VaccinationExpenses> vaccinationExpenses = vaccinationExpensesRepository.findByEmail(email)
                    .stream()
                    .filter(e -> e.getDate().compareTo(fromDate) >= 0 && e.getDate().compareTo(toDate) <= 0)
                    .toList();

            List<MedicineExpenses> medicineExpenses = medicineExpensesRepository.findByEmail(email)
                    .stream()
                    .filter(e -> e.getDate().compareTo(fromDate) >= 0 && e.getDate().compareTo(toDate) <= 0)
                    .toList();

            List<ShedExpenses> shedExpenses = shedExpensesRepository.findByEmail(email)
                    .stream()
                    .filter(e -> e.getDate().compareTo(fromDate) >= 0 && e.getDate().compareTo(toDate) <= 0)
                    .toList();

            List<OtherExpenses> otherExpenses = otherExpensesRepository.findByEmail(email)
                    .stream()
                    .filter(e -> e.getDate().compareTo(fromDate) >= 0 && e.getDate().compareTo(toDate) <= 0)
                    .toList();

            // Calculate total expenses
            double totalFoodExpenses = foodExpenses.stream().mapToDouble(FoodExpenses::getCost).sum();
            double totalVaccinationExpenses = vaccinationExpenses.stream().mapToDouble(VaccinationExpenses::getCost).sum();
            double totalMedicineExpenses = medicineExpenses.stream().mapToDouble(MedicineExpenses::getCost).sum();
            double totalShedExpenses = shedExpenses.stream().mapToDouble(ShedExpenses::getCost).sum();
            double totalOtherExpenses = otherExpenses.stream().mapToDouble(OtherExpenses::getCost).sum();

            double totalExpenses = totalFoodExpenses + totalVaccinationExpenses + totalMedicineExpenses +
                    totalShedExpenses + totalOtherExpenses;

            // Calculate profit
            double profit = totalSales - totalExpenses;

            // Prepare response
            Map<String, Double> response = new HashMap<>();
            response.put("totalSales", totalSales);
            response.put("totalExpenses", totalExpenses);
            response.put("profit", profit);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}