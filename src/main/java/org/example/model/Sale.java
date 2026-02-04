package org.example.model;

import java.time.LocalDateTime;

public class Sale {
    private int id;
    private LocalDateTime saleDate;
    private int userId;
    private double totalAmount;

    public Sale(int id, LocalDateTime saleDate, int userId, double totalAmount) {
        this.id = id;
        this.saleDate = saleDate;
        this.userId = userId;
        this.totalAmount = totalAmount;
    }

    public Sale(int userId, double totalAmount) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.saleDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public int getUserId() {
        return userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
