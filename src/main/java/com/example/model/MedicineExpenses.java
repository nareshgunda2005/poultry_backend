package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MedicineExpenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String medicineName; // Specific to Medicine expenses
    private double cost;

    // No-args constructor required by JPA
    public MedicineExpenses() {
    }

    // Parameterized constructor (optional)
    public MedicineExpenses(Long id, String date, String medicineName, double cost) {
        this.id = id;
        this.date = date;
        this.medicineName = medicineName;
        this.cost = cost;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}