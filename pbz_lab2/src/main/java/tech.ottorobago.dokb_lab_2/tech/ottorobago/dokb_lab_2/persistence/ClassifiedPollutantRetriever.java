package tech.ottorobago.dokb_lab_2.persistence;

import tech.ottorobago.dokb_lab_2.domain.ClassifiedPollutant;

import java.util.List;

public interface ClassifiedPollutantRetriever {
    ClassifiedPollutant loadClassifiedPollutant(int id) throws PersistenceException;

    List<ClassifiedPollutant> loadClassifiedPollutantsByCompany(int company) throws PersistenceException;
}
