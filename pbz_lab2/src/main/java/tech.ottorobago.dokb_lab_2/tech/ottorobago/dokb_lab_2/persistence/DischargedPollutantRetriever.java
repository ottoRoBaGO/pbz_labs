package tech.ottorobago.dokb_lab_2.persistence;

import tech.ottorobago.dokb_lab_2.domain.DischargedPollutant;

import java.util.List;

public interface DischargedPollutantRetriever {
    DischargedPollutant loadDischargedPollutant(int id) throws PersistenceException;

    List<DischargedPollutant> loadDischargedPollutantByDischarge(int discharge) throws PersistenceException;
}
