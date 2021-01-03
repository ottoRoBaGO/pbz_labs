package tech.ottorobago.dokb_lab_2.application.data;

import tech.ottorobago.dokb_lab_2.domain.DischargedPollutant;
import tech.ottorobago.dokb_lab_2.domain.factories.DischargedPollutantFactory;

public class DefaultDischargedPollutantDataToDischargedPollutantConverter implements DischargedPollutantDataToDischargedPollutantConverter {
    private DischargedPollutantFactory dischargedPollutantFactory;

    public DefaultDischargedPollutantDataToDischargedPollutantConverter(DischargedPollutantFactory dischargedPollutantFactory) {
        this.dischargedPollutantFactory = dischargedPollutantFactory;
    }

    @Override
    public DischargedPollutant convertDischargedPollutantDataToDischargedPollutant(DischargedPollutantData dischargedPollutantData) {
        return dischargedPollutantFactory.createDischargedPollutant(dischargedPollutantData.getId(),
                dischargedPollutantData.getDischarge(), dischargedPollutantData.getPollutant(),
                dischargedPollutantData.getConcentration(), dischargedPollutantData.getNcc(),
                dischargedPollutantData.getBackgroundConcentration(), dischargedPollutantData.getMpc());
    }
}
