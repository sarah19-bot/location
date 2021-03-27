package sn.diallo.repository;

import sn.diallo.domain.Local;
import sn.diallo.domain.Personne;

public interface PersonneRepository {
    int add(Personne personne);
    int update(Personne personne);
    int delete(int id);
    Personne[] getAll();
    Personne[] getAllByType(String type);
    Personne getById(int id);
    Personne getByLocal(Local local);
    int louerLocal(Local local);
}
