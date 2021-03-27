package sn.diallo.controller;

import sn.diallo.repository.LocalRepository;
import sn.diallo.repository.PersonneRepository;
import sn.diallo.repository.jdbc.DataSource;
import sn.diallo.repository.jdbc.JdbcBasedLocalRepository;
import sn.diallo.repository.jdbc.JdbcBasedPersonneRepository;
import sn.diallo.repository.jdbc.MySqlDataSource;
import sn.diallo.service.DisplayPersonne;
import sn.diallo.service.MenuPersonne;
import sn.diallo.service.console.ConsoleDisplayPersonne;
import sn.diallo.service.console.ScannerMenuPersonne;

public class LocataireController {
    private final DisplayPersonne displayPersonne ;
    private final MenuPersonne scannerMenuPersonne ;

    public LocataireController(){
        DataSource dataSource = new MySqlDataSource();
        displayPersonne = new ConsoleDisplayPersonne();
        LocalRepository localRepository = new JdbcBasedLocalRepository(dataSource);
        PersonneRepository proprietaireRepository = new JdbcBasedPersonneRepository(dataSource);
        PersonneRepository locataireRepository = new JdbcBasedPersonneRepository(dataSource);
        scannerMenuPersonne = new ScannerMenuPersonne(proprietaireRepository, localRepository, displayPersonne);
    }

    public void process(){

        displayPersonne.afficherMenuParType("locataire");
        scannerMenuPersonne.afficherMenuLocataire();
    }
}
