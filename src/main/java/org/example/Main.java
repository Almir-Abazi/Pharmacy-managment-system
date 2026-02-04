package org.example;

import org.example.model.User;
import org.example.ui.LoginUI;
import org.example.ui.MainMenuUI;

public class Main {
    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();
        User user = loginUI.start();

        MainMenuUI menuUI = new MainMenuUI();
        menuUI.start(user);
    }
}
