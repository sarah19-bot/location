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

public class ProprietaireController {
    private final DisplayPersonne displayProprietaire;
    private final ScannerMenuPersonne scannerMenuProprietaire;

    public ProprietaireController(){
        displayProprietaire = new ConsoleDisplayPersonne();
        DataSource dataSource = new MySqlDataSource();
        LocalRepository localRepository = new JdbcBasedLocalRepository(dataSource);
        PersonneRepository proprietaireRepository = new JdbcBasedPersonneRepository(dataSource);
        scannerMenuProprietaire = new ScannerMenuPersonne(proprietaireRepository, localRepository, displayProprietaire);
    }

    public void process(){
        displayProprietaire.afficherMenuParType("proprietaire");
        scannerMenuProprietaire.afficherMenuProprietaire();
    }
}
