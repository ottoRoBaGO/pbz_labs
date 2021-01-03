package tech.ottorobago.dokb_lab_2.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.data.ClassifiedPollutantData;
import tech.ottorobago.dokb_lab_2.application.data.ClassifiedPollutantDataToClassifiedPollutantConverter;
import tech.ottorobago.dokb_lab_2.persistence.ClassifiedPollutantPlacer;
import tech.ottorobago.dokb_lab_2.persistence.PersistenceException;

public class DefaultChangeClassifiedPollutantUseCase implements ChangeClassifiedPollutantUseCase {
    private ClassifiedPollutantPlacer classifiedPollutantPlacer;
    private ClassifiedPollutantDataToClassifiedPollutantConverter classifiedPollutantDataToClassifiedPollutantConverter;

    private static final Logger logger = LogManager.getLogger(DefaultChangeClassifiedPollutantUseCase.class.getName());

    public DefaultChangeClassifiedPollutantUseCase(ClassifiedPollutantPlacer classifiedPollutantPlacer,
                                                   ClassifiedPollutantDataToClassifiedPollutantConverter classifiedPollutantDataToClassifiedPollutantConverter) {
        this.classifiedPollutantPlacer = classifiedPollutantPlacer;
        this.classifiedPollutantDataToClassifiedPollutantConverter = classifiedPollutantDataToClassifiedPollutantConverter;
    }

    @Override
    public void changeClassifiedPollutant(ClassifiedPollutantData classifiedPollutantData) throws ApplicationException {
        try {
            classifiedPollutantPlacer.saveClassifiedPollutant(
                    classifiedPollutantDataToClassifiedPollutantConverter
                            .convertClassifiedPollutantDataToClassifiedPollutant(classifiedPollutantData));
        } catch (PersistenceException exc) {
            String mes = "Unable to update data in the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }
}