package com.example.repository;

import com.example.model.VaccinationExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VaccinationExpensesRepository extends JpaRepository<VaccinationExpenses, Long> {
    List<VaccinationExpenses> findByEmail(@Param("email") String email);

    @Query("SELECT e FROM VaccinationExpenses e WHERE e.email = :email AND e.date = :date")
    List<VaccinationExpenses> findByEmailAndDate(@Param("email") String email, @Param("date") String date);

    @Modifying
    @Query("DELETE FROM VaccinationExpenses e WHERE e.email = :email")
    void deleteByEmail(@Param("email") String email);
}