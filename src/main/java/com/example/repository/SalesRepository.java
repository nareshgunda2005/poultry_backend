package com.example.repository;

import com.example.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Long> {
}