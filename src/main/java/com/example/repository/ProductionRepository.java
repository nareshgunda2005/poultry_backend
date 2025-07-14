package com.example.repository;

import com.example.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductionRepository extends JpaRepository<Production, Long> {
    List<Production> findByEmail(@Param("email") String email);

    @Query("SELECT p FROM Production p WHERE p.email = :email AND p.date = :date")
    Optional<Production> findByEmailAndDate(@Param("email") String email, @Param("date") String date);

    @Query("SELECT p FROM Production p WHERE p.email = :email AND p.date BETWEEN :startDate AND :endDate")
    List<Production> findByEmailAndDateBetween(@Param("email") String email, @Param("startDate") String startDate, @Param("endDate") String endDate);

    @Modifying
    @Query("DELETE FROM Production p WHERE p.email = :email")
    void deleteByEmail(@Param("email") String email);
}