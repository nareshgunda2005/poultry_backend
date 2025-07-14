package com.example.repository;

import com.example.model.FoodExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FoodExpensesRepository extends JpaRepository<FoodExpenses, Long> {
    List<FoodExpenses> findByEmail(String email);
}