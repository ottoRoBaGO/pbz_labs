package tech.ottorobago.dokb_lab_2.domain.factories;

import tech.ottorobago.dokb_lab_2.domain.ClassifiedPollutant;

public interface ClassifiedPollutantFactory {
    ClassifiedPollutant createClassifiedPollutant(int id, int company, int pollutant, String dangerClass, String lfvGroup);
}
