package com.example.repository;

import com.example.model.MedicineExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicineExpensesRepository extends JpaRepository<MedicineExpenses, Long> {
    List<MedicineExpenses> findByEmail(@Param("email") String email);

    @Query("SELECT e FROM MedicineExpenses e WHERE e.email = :email AND e.date = :date")
    List<MedicineExpenses> findByEmailAndDate(@Param("email") String email, @Param("date") String date);

    @Modifying
    @Query("DELETE FROM MedicineExpenses e WHERE e.email = :email")
    void deleteByEmail(@Param("email") String email);
}