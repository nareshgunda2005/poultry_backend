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
    private String medicineName;
    private double cost;
    private String email; // Added email field

    public MedicineExpenses() {}
    public MedicineExpenses(Long id, String date, String medicineName, double cost, String email) {
        this.id = id;
        this.date = date;
        this.medicineName = medicineName;
        this.cost = cost;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getMedicineName() { return medicineName; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}