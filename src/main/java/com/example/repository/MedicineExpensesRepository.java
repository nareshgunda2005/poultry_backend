package com.example.repository;

import com.example.model.MedicineExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicineExpensesRepository extends JpaRepository<MedicineExpenses, Long> {
    List<MedicineExpenses> findByEmail(String email);
    void deleteByEmail(String email);
}