package sn.diallo;

import sn.diallo.controller.MainController;

public class App {
    public static void main(String[] args) {
        System.out.println("Initialisation du projet");
        MainController mainController = new MainController();
        mainController.process();
    }
}