package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Variation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;
    private int availableQty;
    private String shortDescription;
    private double price;
    
    public Variation() {
    	
    }
    
    public Variation(int availableQty, String shortDescription, double price) {
        this.availableQty = availableQty;
        this.shortDescription = shortDescription;
        this.price = price;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getAvailableQty() {
        return availableQty;
    }
    
    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }
    
    public String getShortDescription() {
        return shortDescription;
    }
    
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
}
