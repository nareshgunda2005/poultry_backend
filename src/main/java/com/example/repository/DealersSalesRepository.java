package com.example.repository;

import com.example.model.DealersSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DealersSalesRepository extends JpaRepository<DealersSales, Long> {
    List<DealersSales> findByEmail(String email);

    @Modifying
    @Query("DELETE FROM DealersSales s WHERE s.email = :email")
    void deleteByEmail(@Param("email") String email);
}