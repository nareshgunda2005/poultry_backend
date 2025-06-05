package com.example.repository;

import com.example.model.VaccinationExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VaccinationExpensesRepository extends JpaRepository<VaccinationExpenses, Long> {
    List<VaccinationExpenses> findByEmail(String email);
    void deleteByEmail(String email);
}