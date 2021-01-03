package tech.ottorobago.dokb_lab_2.persistence;

import tech.ottorobago.dokb_lab_2.domain.ClassifiedPollutant;

public interface ClassifiedPollutantPlacer {
    /*returns a ClassifiedPollutant instance with set id*/
    ClassifiedPollutant createClassifiedPollutant(ClassifiedPollutant classifiedPollutant) throws PersistenceException;

    void saveClassifiedPollutant(ClassifiedPollutant classifiedPollutant) throws PersistenceException;

    void removeClassifiedPollutant(int id) throws PersistenceException;
}
