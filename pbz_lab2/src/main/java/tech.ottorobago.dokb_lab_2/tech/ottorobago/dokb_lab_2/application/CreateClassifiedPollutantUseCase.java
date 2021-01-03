package tech.ottorobago.dokb_lab_2.application;

import tech.ottorobago.dokb_lab_2.application.data.ClassifiedPollutantData;

public interface CreateClassifiedPollutantUseCase {
    /*returns data with set id*/
    ClassifiedPollutantData createClassifiedPollutant(ClassifiedPollutantData classifiedPollutantData)
            throws ApplicationException;
}
