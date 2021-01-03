package tech.ottorobago.dokb_lab_2.persistence;

import tech.ottorobago.dokb_lab_2.domain.Discharge;

import java.util.Date;
import java.util.List;

public interface DischargeRetriever {
    Discharge loadDischarge(int id) throws PersistenceException;

    List<Discharge> loadDischargesByCompany(int company) throws PersistenceException;

    List<Discharge> loadDischargesByCompanyAndTime(int company, Date since, Date until) throws PersistenceException;
}
