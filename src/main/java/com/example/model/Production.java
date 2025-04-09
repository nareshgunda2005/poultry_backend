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
    private int boxes;
    private double cost;
    private int mortality;
    private int feeds;

    public Production() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public int getEggs() { return eggs; }
    public void setEggs(int eggs) { this.eggs = eggs; }
    public int getBoxes() { return boxes; }
    public void setBoxes(int boxes) { this.boxes = boxes; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
    public int getMortality() { return mortality; }
    public void setMortality(int mortality) { this.mortality = mortality; }
    public int getFeeds() { return feeds; }
    public void setFeeds(int feeds) { this.feeds = feeds; }
}