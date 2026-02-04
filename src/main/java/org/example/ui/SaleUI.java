package org.example.ui;

import org.example.model.SaleItem;
import org.example.model.User;
import org.example.service.MedicineService;
import org.example.service.SaleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaleUI {

    private final SaleService saleService = new SaleService();
    private final MedicineService medicineService = new MedicineService();

    public void start(User user) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("=== Sales Menu ===");
            System.out.println("1. New sale");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    newSale(sc, user);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private void newSale(Scanner sc, User user) {
        List<SaleItem> items = new ArrayList<>();

        while (true) {
            System.out.println();
            System.out.println("Add item:");
            System.out.println("1. Add medicine");
            System.out.println("2. Finish & save");
            System.out.println("0. Cancel");
            System.out.print("Choose: ");

            String c = sc.nextLine().trim();

            if (c.equals("1")) {
                System.out.print("Medicine Id: ");
                int medId = Integer.parseInt(sc.nextLine().trim());

                var med = medicineService.getById(medId);
                if (med == null) {
                    System.out.println("Medicine not found.");
                    continue;
                }

                System.out.println("Selected: " + med.getName() + " | Price: " + med.getPrice() + " | Stock: " + med.getStockQty());

                System.out.print("Qty: ");
                int qty = Integer.parseInt(sc.nextLine().trim());
                if (qty <= 0) {
                    System.out.println("Qty must be > 0");
                    continue;
                }

                items.add(new SaleItem(medId, qty, med.getPrice()));
                System.out.println("Item added.");

            } else if (c.equals("2")) {
                if (items.isEmpty()) {
                    System.out.println("No items. Sale not saved.");
                    return;
                }

                try {
                    int saleId = saleService.createSale(user.getId(), items);
                    System.out.println("Sale saved. SaleId = " + saleId);
                } catch (Exception ex) {
                    System.out.println("Sale failed: " + ex.getMessage());
                }
                return;

            } else if (c.equals("0")) {
                System.out.println("Canceled.");
                return;

            } else {
                System.out.println("Invalid option.");
            }
        }
    }
}
