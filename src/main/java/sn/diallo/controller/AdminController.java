package sn.diallo.controller;

import sn.diallo.repository.LocalRepository;
import sn.diallo.repository.PersonneRepository;
import sn.diallo.repository.jdbc.DataSource;
import sn.diallo.repository.jdbc.JdbcBasedLocalRepository;
import sn.diallo.repository.jdbc.JdbcBasedPersonneRepository;
import sn.diallo.repository.jdbc.MySqlDataSource;
import sn.diallo.service.DisplayPersonne;
import sn.diallo.service.console.ConsoleDisplayPersonne;
import sn.diallo.service.console.ScannerMenuPersonne;

public class AdminController {
    private final DisplayPersonne displayPersonne;
    private final ScannerMenuPersonne scannerMenuPersonne;
    private final LocalRepository localRepository;
    private final PersonneRepository personneRepository;

    public AdminController(){
        displayPersonne= new ConsoleDisplayPersonne();
        DataSource dataSource = new MySqlDataSource();
        personneRepository = new JdbcBasedPersonneRepository(dataSource);
        localRepository = new JdbcBasedLocalRepository(dataSource);
        scannerMenuPersonne = new ScannerMenuPersonne(personneRepository, localRepository, displayPersonne);
    }

    public void process(){
        displayPersonne.afficherMenuParType("admin");
        scannerMenuPersonne.afficherMenuAdmin();
    }
}
