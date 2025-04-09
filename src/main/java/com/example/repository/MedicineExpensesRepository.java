package com.example.repository;

import com.example.model.MedicineExpenses;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineExpensesRepository extends JpaRepository<MedicineExpenses, Long> {

}
