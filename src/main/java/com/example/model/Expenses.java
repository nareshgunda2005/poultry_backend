package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String type;
    private String name;
    private double cost;

    public Expenses() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
}