// com.example.model.FoodExpenses.java
package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FoodExpenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String foodName; // Renamed from 'name' for clarity
    private double cost;

    // Constructors, getters, setters
    public FoodExpenses() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
}

