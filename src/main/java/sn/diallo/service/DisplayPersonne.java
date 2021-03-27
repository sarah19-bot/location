package sn.diallo.service;

import sn.diallo.domain.Local;
import sn.diallo.domain.Personne;

public interface DisplayPersonne {
    void afficherMenuParType(String type);
    void afficherMesLocaux(Local[] locals);
    Local addLocal(Personne proprietaire);
    Local updateLocal(Local local);
    int deleteLocal();
    Personne addPersonne(Local local);
    Personne updatePersonne(Personne personne);
    int deletePersonne();
    void louerLocal(Local local);
    void afficherOptionInconnue();
}
