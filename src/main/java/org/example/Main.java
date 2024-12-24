package org.example;

import org.example.view.MainMenu;

public class Main {
    public static void main(String[] args) {

        MainMenu mainMenu = new MainMenu();
        while (true) {
            mainMenu.mainMenu();
        }
    }
}