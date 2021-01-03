package tech.ottorobago.dokb_lab_2.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.persistence.ClassifiedPollutantPlacer;
import tech.ottorobago.dokb_lab_2.persistence.PersistenceException;

public class DefaultRemoveClassifiedPollutantUseCase implements RemoveClassifiedPollutantUseCase {
    private ClassifiedPollutantPlacer classifiedPollutantPlacer;

    private static final Logger logger = LogManager.getLogger(DefaultRemoveClassifiedPollutantUseCase.class.getName());

    public DefaultRemoveClassifiedPollutantUseCase(ClassifiedPollutantPlacer classifiedPollutantPlacer) {
        this.classifiedPollutantPlacer = classifiedPollutantPlacer;
    }

    @Override
    public void removeClassifiedPollutant(int classifiedPollutant) throws ApplicationException {
        try {
            classifiedPollutantPlacer.removeClassifiedPollutant(classifiedPollutant);
        } catch (PersistenceException exc) {
            String mes = "Unable to remove data from the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }
}
