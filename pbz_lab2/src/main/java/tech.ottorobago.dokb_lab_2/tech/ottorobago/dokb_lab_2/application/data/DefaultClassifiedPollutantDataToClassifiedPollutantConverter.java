package tech.ottorobago.dokb_lab_2.application.data;

import tech.ottorobago.dokb_lab_2.domain.ClassifiedPollutant;
import tech.ottorobago.dokb_lab_2.domain.factories.ClassifiedPollutantFactory;

public class DefaultClassifiedPollutantDataToClassifiedPollutantConverter
        implements ClassifiedPollutantDataToClassifiedPollutantConverter {
    private ClassifiedPollutantFactory classifiedPollutantFactory;

    public DefaultClassifiedPollutantDataToClassifiedPollutantConverter(ClassifiedPollutantFactory classifiedPollutantFactory) {
        this.classifiedPollutantFactory = classifiedPollutantFactory;
    }

    @Override
    public ClassifiedPollutant convertClassifiedPollutantDataToClassifiedPollutant(ClassifiedPollutantData classifiedPollutantData) {
        return classifiedPollutantFactory.createClassifiedPollutant(classifiedPollutantData.getId(),
                classifiedPollutantData.getCompany(), classifiedPollutantData.getPollutant(),
                classifiedPollutantData.getDangerClass(), classifiedPollutantData.getLfvGroup());
    }
}
