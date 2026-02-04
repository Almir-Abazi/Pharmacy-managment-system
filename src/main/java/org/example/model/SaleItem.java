package org.example.model;

public class SaleItem {
    private int medicineId;
    private int qty;
    private double unitPrice;

    public SaleItem(int medicineId, int qty, double unitPrice) {
        this.medicineId = medicineId;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public int getQty() {
        return qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getLineTotal() {
        return unitPrice * qty;
    }
}
