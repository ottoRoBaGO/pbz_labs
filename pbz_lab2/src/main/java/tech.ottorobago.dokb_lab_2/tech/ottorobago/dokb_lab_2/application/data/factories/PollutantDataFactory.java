package tech.ottorobago.dokb_lab_2.application.data.factories;

import tech.ottorobago.dokb_lab_2.application.data.PollutantData;

public interface PollutantDataFactory {
    PollutantData createPollutantData(int id, String name);
}
