package com.example.repository;

import com.example.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
    List<Expenses> findByEmail(@Param("email") String email);

    @Query("SELECT e FROM Expenses e WHERE e.email = :email AND e.date = :date")
    List<Expenses> findByEmailAndDate(@Param("email") String email, @Param("date") String date);

    @Modifying
    @Query("DELETE FROM Expenses e WHERE e.email = :email")
    void deleteByEmail(@Param("email") String email);
}