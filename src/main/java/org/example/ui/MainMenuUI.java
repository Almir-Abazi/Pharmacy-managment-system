package org.example.ui;

import org.example.model.User;

import java.util.Scanner;

public class MainMenuUI {

    public void start(User user) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("=== Pharmacy Management System ===");
            System.out.println("Logged as: " + user.getUsername() + " | RoleId: " + user.getRoleId());
            System.out.println("----------------------------------");

            if (user.getRoleId() == 1) {
                System.out.println("1. Suppliers");
                System.out.println("2. Medicines");
                System.out.println("3. Sales");
                System.out.println("0. Exit");
            } else {
                System.out.println("3. Sales");
                System.out.println("0. Exit");
            }

            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();

            if (user.getRoleId() == 1) {
                switch (choice) {
                    case "1":
                        new SupplierUI().start();
                        break;
                    case "2":
                        new MedicineUI().start();
                        break;
                    case "3":
                        new SaleUI().start(user);
                        break;
                    case "0":
                        System.out.println("Bye.");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } else {
                switch (choice) {
                    case "3":
                        new SaleUI().start(user);
                        break;
                    case "0":
                        System.out.println("Bye.");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            }
        }
    }
}
