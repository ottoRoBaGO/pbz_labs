package tech.ottorobago.dokb_lab_2.application;

import tech.ottorobago.dokb_lab_2.application.data.DischargedPollutantData;

public interface CreateDischargedPollutantUseCase {
    /*returns data with set id*/
    DischargedPollutantData createDischargedPollutant(DischargedPollutantData dischargedPollutantData)
            throws ApplicationException;
}
