package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StartNew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numberOfBirds;
    private double cost;
    private String purchaseCompany;
    private String email;

    public StartNew() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getNumberOfBirds() { return numberOfBirds; }
    public void setNumberOfBirds(int numberOfBirds) { this.numberOfBirds = numberOfBirds; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
    public String getPurchaseCompany() { return purchaseCompany; }
    public void setPurchaseCompany(String purchaseCompany) { this.purchaseCompany = purchaseCompany; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}