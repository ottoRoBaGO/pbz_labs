package tech.ottorobago.dokb_lab_2.application;

import tech.ottorobago.dokb_lab_2.application.data.ClassifiedPollutantData;

public interface ChangeClassifiedPollutantUseCase {
    void changeClassifiedPollutant(ClassifiedPollutantData classifiedPollutantData) throws ApplicationException;
}
