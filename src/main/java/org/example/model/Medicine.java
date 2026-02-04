package org.example.model;

import java.time.LocalDate;

public class Medicine {
    private int id;
    private String name;
    private String barcode;
    private double price;
    private int stockQty;
    private LocalDate expiryDate;
    private int supplierId;

    public Medicine(int id, String name, String barcode, double price, int stockQty, LocalDate expiryDate, int supplierId) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.price = price;
        this.stockQty = stockQty;
        this.expiryDate = expiryDate;
        this.supplierId = supplierId;
    }

    public Medicine(String name, String barcode, double price, int stockQty, LocalDate expiryDate, int supplierId) {
        this.name = name;
        this.barcode = barcode;
        this.price = price;
        this.stockQty = stockQty;
        this.expiryDate = expiryDate;
        this.supplierId = supplierId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQty() {
        return stockQty;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public int getSupplierId() {
        return supplierId;
    }
}
