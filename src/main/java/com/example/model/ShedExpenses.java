package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShedExpenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String shedDescription; // Renamed for clarity
    private double cost;
    private String email; // Added email field

    public ShedExpenses() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getShedDescription() { return shedDescription; }
    public void setShedDescription(String shedDescription) { this.shedDescription = shedDescription; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}