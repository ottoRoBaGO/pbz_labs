package tech.ottorobago.dokb_lab_2.persistence;

import tech.ottorobago.dokb_lab_2.domain.Discharge;

public interface DischargePlacer {
    /*returns a Discharge instance with set id*/
    Discharge createDischarge(Discharge discharge) throws PersistenceException;

    void saveDischarge(Discharge discharge) throws PersistenceException;

    void removeDischarge(int id) throws PersistenceException;
}
