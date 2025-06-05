package com.example.repository;

import com.example.model.OutersSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OutersSalesRepository extends JpaRepository<OutersSales, Long> {
    List<OutersSales> findByEmail(String email);

    @Modifying
    @Query("DELETE FROM OutersSales s WHERE s.email = :email")
    void deleteByEmail(@Param("email") String email);
}