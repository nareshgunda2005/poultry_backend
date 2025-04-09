package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TurnOver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromDate;
    private String toDate;
    private double totalExpenses;
    private double totalSales;
    private double profit;

    // Constructors
    public TurnOver() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFromDate() { return fromDate; }
    public void setFromDate(String fromDate) { this.fromDate = fromDate; }
    public String getToDate() { return toDate; }
    public void setToDate(String toDate) { this.toDate = toDate; }
    public double getTotalExpenses() { return totalExpenses; }
    public void setTotalExpenses(double totalExpenses) { this.totalExpenses = totalExpenses; }
    public double getTotalSales() { return totalSales; }
    public void setTotalSales(double totalSales) { this.totalSales = totalSales; }
    public double getProfit() { return profit; }
    public void setProfit(double profit) { this.profit = profit; }
}
