package tech.ottorobago.dokb_lab_2.application.data;

import tech.ottorobago.dokb_lab_2.domain.DischargedPollutant;

public interface DischargedPollutantDataToDischargedPollutantConverter {
    DischargedPollutant convertDischargedPollutantDataToDischargedPollutant(DischargedPollutantData dischargedPollutantData);
}
