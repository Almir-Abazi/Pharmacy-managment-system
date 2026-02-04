package org.example.ui;

import org.example.model.Medicine;
import org.example.service.MedicineService;

import java.time.LocalDate;
import java.util.Scanner;

public class MedicineUI {

    private final MedicineService medicineService = new MedicineService();

    public void start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("=== Medicines Menu ===");
            System.out.println("1. List medicines");
            System.out.println("2. Add medicine");
            System.out.println("3. Update medicine");
            System.out.println("4. Delete medicine");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    listMedicines();
                    break;
                case "2":
                    addMedicine(sc);
                    break;
                case "3":
                    updateMedicine(sc);
                    break;
                case "4":
                    deleteMedicine(sc);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void listMedicines() {
        var list = medicineService.getAll();
        System.out.println("--- Medicines ---");
        if (list.isEmpty()) {
            System.out.println("No medicines found.");
            return;
        }
        for (Medicine m : list) {
            System.out.println(
                    m.getId() + " | " +
                            m.getName() + " | " +
                            m.getBarcode() + " | " +
                            m.getPrice() + " | " +
                            m.getStockQty() + " | " +
                            m.getExpiryDate() + " | " +
                            m.getSupplierId()
            );
        }
    }

    private void addMedicine(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Barcode: ");
        String barcode = sc.nextLine().trim();

        System.out.print("Price: ");
        double price = readDouble(sc);

        System.out.print("Stock Qty: ");
        int stockQty = readInt(sc);

        System.out.print("Expiry Date (YYYY-MM-DD): ");
        LocalDate expiry = LocalDate.parse(sc.nextLine().trim());

        System.out.print("Supplier Id: ");
        int supplierId = readInt(sc);

        medicineService.create(new Medicine(name, barcode, price, stockQty, expiry, supplierId));
        System.out.println("Medicine created.");
    }

    private void updateMedicine(Scanner sc) {
        System.out.print("Medicine Id: ");
        int id = readInt(sc);

        System.out.print("Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Barcode: ");
        String barcode = sc.nextLine().trim();

        System.out.print("Price: ");
        double price = readDouble(sc);

        System.out.print("Stock Qty: ");
        int stockQty = readInt(sc);

        System.out.print("Expiry Date (YYYY-MM-DD): ");
        LocalDate expiry = LocalDate.parse(sc.nextLine().trim());

        System.out.print("Supplier Id: ");
        int supplierId = readInt(sc);

        medicineService.update(id, new Medicine(name, barcode, price, stockQty, expiry, supplierId));
        System.out.println("Medicine updated.");
    }

    private void deleteMedicine(Scanner sc) {
        System.out.print("Medicine Id: ");
        int id = readInt(sc);

        boolean ok = medicineService.delete(id);
        if (ok) {
            System.out.println("Medicine deleted.");
        } else {
            System.out.println("Cannot delete medicine (it may be used in sales).");
        }
    }

    private int readInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }

    private double readDouble(Scanner sc) {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }
}
