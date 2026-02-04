package org.example.ui;

import org.example.model.Supplier;
import org.example.service.SupplierService;

import java.util.Scanner;

public class SupplierUI {

    private final SupplierService supplierService = new SupplierService();

    public void start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("=== Suppliers Menu ===");
            System.out.println("1. List suppliers");
            System.out.println("2. Add supplier");
            System.out.println("3. Update supplier");
            System.out.println("4. Delete supplier");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    listSuppliers();
                    break;
                case "2":
                    addSupplier(sc);
                    break;
                case "3":
                    updateSupplier(sc);
                    break;
                case "4":
                    deleteSupplier(sc);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private void listSuppliers() {
        var list = supplierService.getAll();
        System.out.println("--- Suppliers ---");
        if (list.isEmpty()) {
            System.out.println("No suppliers found.");
            return;
        }
        for (Supplier s : list) {
            System.out.println(s.getId() + " | " + s.getName() + " | " + s.getPhone() + " | " + s.getAddress());
        }
    }

    private void addSupplier(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Phone: ");
        String phone = sc.nextLine().trim();

        System.out.print("Address / Email: ");
        String address = sc.nextLine().trim();

        supplierService.create(new Supplier(name, phone, address));
        System.out.println("Supplier created.");
    }

    private void updateSupplier(Scanner sc) {
        System.out.print("Supplier Id: ");
        int id = readInt(sc);

        System.out.print("Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Phone: ");
        String phone = sc.nextLine().trim();

        System.out.print("Address / Email: ");
        String address = sc.nextLine().trim();

        supplierService.update(id, new Supplier(name, phone, address));
        System.out.println("Supplier updated.");
    }

    private void deleteSupplier(Scanner sc) {
        System.out.print("Supplier Id: ");
        int id = readInt(sc);

        boolean ok = supplierService.delete(id);
        if (ok) {
            System.out.println("Supplier deleted.");
        } else {
            System.out.println("Cannot delete supplier (it has medicines).");
        }
    }

    private int readInt(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (Exception e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }
}
