package com.example.repository;

import com.example.model.FoodExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodExpensesRepository extends JpaRepository<FoodExpenses, Long> {
    List<FoodExpenses> findByEmail(String email);

    @Modifying
    @Query("DELETE FROM FoodExpenses e WHERE e.email = :email")
    void deleteByEmail(@Param("email") String email);
}