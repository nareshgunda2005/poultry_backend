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
    private FoodExpensesRepository foodExpensesRepository;
    @Autowired
    private VaccinationExpensesRepository vaccinationExpensesRepository;
    @Autowired
    private MedicineExpensesRepository medicineExpensesRepository;
    @Autowired
    private ShedExpensesRepository shedExpensesRepository;
    @Autowired
    private OtherExpensesRepository otherExpensesRepository;
    @Autowired
    private OutersSalesRepository outersSalesRepository;
    @Autowired
    private DealersSalesRepository dealersSalesRepository;
    @Autowired
    private TurnOverRepository turnOverRepository;

    @GetMapping("/calculate")
    public ResponseEntity<Map<String, Double>> calculateTurnOver(
            @RequestParam String fromDate,
            @RequestParam String toDate) {
        
        // Get expenses from all tables between dates
        List<FoodExpenses> foodExpenses = foodExpensesRepository.findAll()
            .stream()
            .filter(exp -> exp.getDate().compareTo(fromDate) >= 0 && exp.getDate().compareTo(toDate) <= 0)
            .toList();
        List<VaccinationExpenses> vaccinationExpenses = vaccinationExpensesRepository.findAll()
            .stream()
            .filter(exp -> exp.getDate().compareTo(fromDate) >= 0 && exp.getDate().compareTo(toDate) <= 0)
            .toList();
        List<MedicineExpenses> medicineExpenses = medicineExpensesRepository.findAll()
            .stream()
            .filter(exp -> exp.getDate().compareTo(fromDate) >= 0 && exp.getDate().compareTo(toDate) <= 0)
            .toList();
        List<ShedExpenses> shedExpenses = shedExpensesRepository.findAll()
            .stream()
            .filter(exp -> exp.getDate().compareTo(fromDate) >= 0 && exp.getDate().compareTo(toDate) <= 0)
            .toList();
        List<OtherExpenses> otherExpenses = otherExpensesRepository.findAll()
            .stream()
            .filter(exp -> exp.getDate().compareTo(fromDate) >= 0 && exp.getDate().compareTo(toDate) <= 0)
            .toList();
        
        // Get sales between dates
        List<OutersSales> outersSales = outersSalesRepository.findAll()
            .stream()
            .filter(sale -> sale.getDate().compareTo(fromDate) >= 0 && sale.getDate().compareTo(toDate) <= 0)
            .toList();
        List<DealersSales> dealersSales = dealersSalesRepository.findAll()
            .stream()
            .filter(sale -> sale.getDate().compareTo(fromDate) >= 0 && sale.getDate().compareTo(toDate) <= 0)
            .toList();

        // Calculate totals
        double totalFoodExpenses = foodExpenses.stream().mapToDouble(FoodExpenses::getCost).sum();
        double totalVaccinationExpenses = vaccinationExpenses.stream().mapToDouble(VaccinationExpenses::getCost).sum();
        double totalMedicineExpenses = medicineExpenses.stream().mapToDouble(MedicineExpenses::getCost).sum();
        double totalShedExpenses = shedExpenses.stream().mapToDouble(ShedExpenses::getCost).sum();
        double totalOtherExpenses = otherExpenses.stream().mapToDouble(OtherExpenses::getCost).sum();
        double totalExpenses = totalFoodExpenses + totalVaccinationExpenses + totalMedicineExpenses + totalShedExpenses + totalOtherExpenses;

        double totalOutersSales = outersSales.stream().mapToDouble(OutersSales::getAmount).sum();
        double totalDealersSales = dealersSales.stream().mapToDouble(DealersSales::getAmount).sum();
        double totalSales = totalOutersSales + totalDealersSales;
        double profit = totalSales - totalExpenses;

        // Persist the calculation (optional)
        TurnOver turnOver = new TurnOver();
        turnOver.setFromDate(fromDate);
        turnOver.setToDate(toDate);
        turnOver.setTotalExpenses(totalExpenses);
        turnOver.setTotalSales(totalSales);
        turnOver.setProfit(profit);
        turnOverRepository.save(turnOver);

        // Prepare response
        Map<String, Double> response = new HashMap<>();
        response.put("totalExpenses", totalExpenses);
        response.put("totalSales", totalSales);
        response.put("profit", profit);

        return ResponseEntity.ok(response);
    }
}