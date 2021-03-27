package sn.diallo.repository;

import sn.diallo.domain.Local;
import sn.diallo.domain.Personne;

public interface LocalRepository {
    int add(Local local, Personne proprietaire);
    int update(Local local, Personne proprietaire);
    int delete(int id);
    Local[] getAll();
    Local[] getAllByType(String type);
    Local[] getAllByProprietaire(Personne proprietaire);
    Local[] getAllByTypeAndProprietaire(String Type, Personne proprietaire);
    Local[] getAllByLocataire(Personne locataire);
    Local getById(int id);

}
