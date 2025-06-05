package com.example.repository;

import com.example.model.OtherExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OtherExpensesRepository extends JpaRepository<OtherExpenses, Long> {
    List<OtherExpenses> findByEmail(String email);
    void deleteByEmail(String email);
}