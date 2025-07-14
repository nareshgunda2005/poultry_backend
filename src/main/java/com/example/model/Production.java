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
    private String trays;
    private Integer eggs;
    private Double boxes;
    private Double cost;
    private Integer mortality;
    private Double feeds;
    private Integer doubleEggs;
    private Integer damagedEggs;
    private String workerPresence;
    private Double productionPercentage;
    private String email;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getTrays() { return trays; }
    public void setTrays(String trays) { this.trays = trays; }
    public Integer getEggs() { return eggs; }
    public void setEggs(Integer eggs) { this.eggs = eggs; }
    public Double getBoxes() { return boxes; }
    public void setBoxes(Double boxes) { this.boxes = boxes; }
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
    public Integer getMortality() { return mortality; }
    public void setMortality(Integer mortality) { this.mortality = mortality; }
    public Double getFeeds() { return feeds; }
    public void setFeeds(Double feeds) { this.feeds = feeds; }
    public Integer getDoubleEggs() { return doubleEggs; }
    public void setDoubleEggs(Integer doubleEggs) { this.doubleEggs = doubleEggs; }
    public Integer getDamagedEggs() { return damagedEggs; }
    public void setDamagedEggs(Integer damagedEggs) { this.damagedEggs = damagedEggs; }
    public String getWorkerPresence() { return workerPresence; }
    public void setWorkerPresence(String workerPresence) { this.workerPresence = workerPresence; }
    public Double getProductionPercentage() { return productionPercentage; }
    public void setProductionPercentage(Double productionPercentage) { this.productionPercentage = productionPercentage; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}