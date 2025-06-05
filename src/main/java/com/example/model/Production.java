package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private int eggs;
    private String trays; // Changed to String to store decimal format (e.g., "1.10")
    private int boxes;
    private double cost;
    private int mortality;
    private int feeds;
    private int doubleEggs;
    private int damagedEggs;
    private String workerPresence;
    private double productionPercentage; // New field
    private String email;

    public Production() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public int getEggs() { return eggs; }
    public void setEggs(int eggs) { this.eggs = eggs; }
    public String getTrays() { return trays; }
    public void setTrays(String trays) { this.trays = trays; }
    public int getBoxes() { return boxes; }
    public void setBoxes(int boxes) { this.boxes = boxes; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
    public int getMortality() { return mortality; }
    public void setMortality(int mortality) { this.mortality = mortality; }
    public int getFeeds() { return feeds; }
    public void setFeeds(int feeds) { this.feeds = feeds; }
    public int getDoubleEggs() { return doubleEggs; }
    public void setDoubleEggs(int doubleEggs) { this.doubleEggs = doubleEggs; }
    public int getDamagedEggs() { return damagedEggs; }
    public void setDamagedEggs(int damagedEggs) { this.damagedEggs = damagedEggs; }
    public String getWorkerPresence() { return workerPresence; }
    public void setWorkerPresence(String workerPresence) { this.workerPresence = workerPresence; }
    public double getProductionPercentage() { return productionPercentage; }
    public void setProductionPercentage(double productionPercentage) { this.productionPercentage = productionPercentage; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}