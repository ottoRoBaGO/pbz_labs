package tech.ottorobago.dokb_lab_2.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.data.ClassifiedPollutantData;
import tech.ottorobago.dokb_lab_2.application.data.ClassifiedPollutantDataToClassifiedPollutantConverter;
import tech.ottorobago.dokb_lab_2.persistence.ClassifiedPollutantPlacer;
import tech.ottorobago.dokb_lab_2.persistence.PersistenceException;

public class DefaultCreateClassifiedPollutantUseCase implements CreateClassifiedPollutantUseCase {
    private ClassifiedPollutantPlacer classifiedPollutantPlacer;
    private ClassifiedPollutantDataToClassifiedPollutantConverter classifiedPollutantDataToClassifiedPollutantConverter;

    private static final Logger logger = LogManager.getLogger(DefaultCreateClassifiedPollutantUseCase.class.getName());

    public DefaultCreateClassifiedPollutantUseCase(ClassifiedPollutantPlacer classifiedPollutantPlacer,
                                                   ClassifiedPollutantDataToClassifiedPollutantConverter classifiedPollutantDataToClassifiedPollutantConverter) {
        this.classifiedPollutantPlacer = classifiedPollutantPlacer;
        this.classifiedPollutantDataToClassifiedPollutantConverter = classifiedPollutantDataToClassifiedPollutantConverter;
    }

    @Override
    public ClassifiedPollutantData createClassifiedPollutant(ClassifiedPollutantData classifiedPollutantData) throws ApplicationException {
        try {
            int newId = classifiedPollutantPlacer.createClassifiedPollutant(
                    classifiedPollutantDataToClassifiedPollutantConverter
                            .convertClassifiedPollutantDataToClassifiedPollutant(classifiedPollutantData)).getId();

            classifiedPollutantData.setId(newId);

            return classifiedPollutantData;
        } catch (PersistenceException exc) {
            String mes = "Unable to insert data into the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }
}
