package com.example.repository;

import com.example.model.ShedExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShedExpensesRepository extends JpaRepository<ShedExpenses, Long> {
    List<ShedExpenses> findByEmail(@Param("email") String email);

    @Query("SELECT e FROM ShedExpenses e WHERE e.email = :email AND e.date = :date")
    List<ShedExpenses> findByEmailAndDate(@Param("email") String email, @Param("date") String date);

    @Modifying
    @Query("DELETE FROM ShedExpenses e WHERE e.email = :email")
    void deleteByEmail(@Param("email") String email);
}