package com.example.repository;

import com.example.model.OtherExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OtherExpensesRepository extends JpaRepository<OtherExpenses, Long> {
    List<OtherExpenses> findByEmail(@Param("email") String email);

    @Query("SELECT e FROM OtherExpenses e WHERE e.email = :email AND e.date = :date")
    List<OtherExpenses> findByEmailAndDate(@Param("email") String email, @Param("date") String date);

    @Modifying
    @Query("DELETE FROM OtherExpenses e WHERE e.email = :email")
    void deleteByEmail(@Param("email") String email);
}