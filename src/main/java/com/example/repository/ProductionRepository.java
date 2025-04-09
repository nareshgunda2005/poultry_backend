package com.example.repository;

import com.example.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionRepository extends JpaRepository<Production, Long> {
    Production findByDate(String date);
}