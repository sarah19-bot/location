package sn.diallo.service.console;

import sn.diallo.service.DisplayPrincipal;

import java.util.Scanner;

public class ConsoleDisplayPrincipal implements DisplayPrincipal {
    public ConsoleDisplayPrincipal(){

    }

    public void afficherBienvenue() {
        System.out.println("Bienvenu sur la plateforme de location!");
    }

    public void afficherMenuPrincipal() {
        System.out.println("> a pour Admin");
        System.out.println("> p pour Proprietaire");
        System.out.println("> l pour Locataire ");
    }
}
