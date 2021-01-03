package tech.ottorobago.dokb_lab_2.application;

import tech.ottorobago.dokb_lab_2.application.data.DischargeData;

import java.util.Date;
import java.util.List;

public interface RetrieveDischargeUseCase {
    DischargeData retrieveDischarge(int id) throws ApplicationException;

    List<DischargeData> retrieveDischargesByCompany(int company) throws ApplicationException;

    List<DischargeData> retrieveDischargesByCompanyAndTime(int company, Date since, Date until)
            throws ApplicationException;
}
