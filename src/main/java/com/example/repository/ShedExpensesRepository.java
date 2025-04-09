package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.ShedExpenses;

public interface ShedExpensesRepository extends JpaRepository<ShedExpenses, Long> {

}
