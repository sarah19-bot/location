package sn.diallo.service;

import sn.diallo.domain.Local;
import sn.diallo.domain.Personne;

public interface DisplayLocal {
    void afficherListeLocaux(Local[] locals);
    void afficherListeLocataires(Personne[] locataires);
    void afficherListeProprietaires(Personne[] proprietaires);
    void afficherLocauxParProprietaire(Personne proprietaire, Local[] locals);
    void afficherOptionInconnue();
}
