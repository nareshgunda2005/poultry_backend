package com.example.repository;

import com.example.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
}