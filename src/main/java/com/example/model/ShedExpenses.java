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
    private String date; // Ensure this field exists
    private String shedItem; // Specific to Shed expenses
    private double cost;

    // No-args constructor required by JPA
    public ShedExpenses() {
    }

    // Parameterized constructor (optional)
    public ShedExpenses(Long id, String date, String shedItem, double cost) {
        this.id = id;
        this.date = date;
        this.shedItem = shedItem;
        this.cost = cost;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() { // This was missing or undefined
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShedItem() {
        return shedItem;
    }

    public void setShedItem(String shedItem) {
        this.shedItem = shedItem;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}