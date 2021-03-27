package sn.diallo.controller;

import sn.diallo.repository.LocalRepository;
import sn.diallo.repository.PersonneRepository;
import sn.diallo.repository.jdbc.DataSource;
import sn.diallo.repository.jdbc.JdbcBasedLocalRepository;
import sn.diallo.repository.jdbc.JdbcBasedPersonneRepository;
import sn.diallo.repository.jdbc.MySqlDataSource;
import sn.diallo.service.DisplayPersonne;
import sn.diallo.service.DisplayPrincipal;
import sn.diallo.service.MenuPersonne;
import sn.diallo.service.MenuPrincipal;
import sn.diallo.service.console.ConsoleDisplayPersonne;
import sn.diallo.service.console.ConsoleDisplayPrincipal;
import sn.diallo.service.console.ScannerMenuPersonne;
import sn.diallo.service.console.ScannerMenuPrincipal;
import sun.applet.Main;

public class MainController {
    private final DisplayPersonne displayPrincipal;
    private MenuPersonne scannerMenuPrincipal;
    private PersonneRepository personneRepository;
    private LocalRepository localRepository;

    public MainController(){
        DataSource dataSource = new MySqlDataSource();
        displayPrincipal = new ConsoleDisplayPersonne();
        personneRepository = new JdbcBasedPersonneRepository(dataSource);
        localRepository = new JdbcBasedLocalRepository(dataSource);
        scannerMenuPrincipal = new ScannerMenuPersonne(personneRepository, localRepository, displayPrincipal);
    }

    public void process(){
        scannerMenuPrincipal.afficherMenu();
    }
}
