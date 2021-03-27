package sn.diallo.service.console;

import sn.diallo.service.DisplayPrincipal;
import sn.diallo.service.MenuPrincipal;

import java.util.Scanner;

public class ScannerMenuPrincipal implements MenuPrincipal {
    private DisplayPrincipal displayPrincipal;
    private final Scanner scanner;

    public ScannerMenuPrincipal(DisplayPrincipal displayPrincipal){
        this.displayPrincipal = displayPrincipal;
        this.scanner = new Scanner(System.in);
    }

    private String lireChoix() {
        return scanner.next();
    }

    public void afficherMenu(String choix){

    }

    public void afficerMenu() {
        displayPrincipal.afficherBienvenue();
        displayPrincipal.afficherMenuPrincipal();
        scanner.nextLine();
    }

}
