package org.example.ui;

import org.example.model.User;
import org.example.service.AuthService;

import java.util.Scanner;

public class LoginUI {

    private final AuthService authService = new AuthService();

    public User start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Username: ");
            String username = sc.nextLine().trim();

            System.out.print("Password: ");
            String password = sc.nextLine().trim();

            boolean ok = authService.login(username, password);

            if (ok) {
                System.out.println("Login success!");
                return authService.getCurrentUser();
            } else {
                System.out.println("Login failed! Try again.");
            }
        }
    }
}
