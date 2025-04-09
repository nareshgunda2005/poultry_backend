package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VaccinationExpenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String vaccinationFor; // Renamed from 'name'
    private double cost;

    // Constructors, getters, setters
    public VaccinationExpenses() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getVaccinationFor() { return vaccinationFor; }
    public void setVaccinationFor(String vaccinationFor) { this.vaccinationFor = vaccinationFor; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
}
