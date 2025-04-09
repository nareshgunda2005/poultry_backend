// com.example.repository.FoodExpensesRepository.java
package com.example.repository;

import com.example.model.FoodExpenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodExpensesRepository extends JpaRepository<FoodExpenses, Long> {
}

// Similarly for VaccinationExpensesRepository, MedicineExpensesRepository, etc.