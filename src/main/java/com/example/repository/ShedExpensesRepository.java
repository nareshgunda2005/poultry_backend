package com.example.repository;

import com.example.model.ShedExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShedExpensesRepository extends JpaRepository<ShedExpenses, Long> {
    List<ShedExpenses> findByEmail(String email);
    void deleteByEmail(String email);
}