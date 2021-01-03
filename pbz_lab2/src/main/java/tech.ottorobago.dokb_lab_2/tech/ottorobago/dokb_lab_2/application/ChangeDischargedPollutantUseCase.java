package tech.ottorobago.dokb_lab_2.application;

import tech.ottorobago.dokb_lab_2.application.data.DischargedPollutantData;

public interface ChangeDischargedPollutantUseCase {
    void changeDischargedPollutant(DischargedPollutantData dischargedPollutantData) throws ApplicationException;
}
