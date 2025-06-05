package com.example.repository;

import com.example.model.StartNew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StartNewRepository extends JpaRepository<StartNew, Long> {
    Optional<StartNew> findByEmail(String email);

    @Modifying
    @Query("DELETE FROM StartNew s WHERE s.email = :email")
    void deleteByEmail(@Param("email") String email);
}