package sn.diallo.service.console;

import sn.diallo.domain.Local;
import sn.diallo.domain.Personne;
import sn.diallo.repository.PersonneRepository;
import sn.diallo.service.DisplayLocal;

public class ConsoleDisplayLocal implements DisplayLocal {
    private PersonneRepository personneRepository;

    public ConsoleDisplayLocal(PersonneRepository personneRepository){
        this.personneRepository = personneRepository;
    }

    public void afficherListeLocaux(Local[] locals) {
        System.out.println("Les locaux disponibles sont:");
        for (int i = 0; i < locals.length; i++) {
            Local local = locals[i];
            System.out.println(String.format("> %s", local.toString()));
        }
    }

    public void afficherListeLocataires(Personne[] locataires) {
        System.out.println("Les locataires disponibles sont:");
        for (int i = 0; i < locataires.length; i++) {
            Personne locataire = locataires[i];
            System.out.println(String.format("> %s", locataire.toString()));
        }
    }

    public void afficherListeProprietaires(Personne[] proprietaires) {
        System.out.println("Les proprietaires disponibles sont:");
        for (int i = 0; i < proprietaires.length; i++) {
            Personne proprietaire = proprietaires[i];
            System.out.println(String.format("> %s", proprietaire.toString()));
        }
    }

    public void afficherLocauxParProprietaire(Personne proprietaire, Local[] locals) {
        System.out.println(String.format("La liste des locaux de %s :", proprietaire.toString()));
        for (int i = 0; i < locals.length; i++) {
            Local local = locals[i];
            System.out.println(String.format("> %s", local.toString()));
        }
    }

    public void afficherOptionInconnue() {
        System.out.println("Option Inconnue");
    }
}
