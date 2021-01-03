package tech.ottorobago.dokb_lab_2.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.data.DischargedPollutantData;
import tech.ottorobago.dokb_lab_2.application.data.DischargedPollutantDataToDischargedPollutantConverter;
import tech.ottorobago.dokb_lab_2.persistence.DischargedPollutantPlacer;
import tech.ottorobago.dokb_lab_2.persistence.PersistenceException;

public class DefaultChangeDischargedPollutantUseCase implements ChangeDischargedPollutantUseCase {
    private DischargedPollutantPlacer dischargedPollutantPlacer;
    private DischargedPollutantDataToDischargedPollutantConverter dischargedPollutantDataToDischargedPollutantConverter;

    private static final Logger logger = LogManager.getLogger(DefaultChangeDischargedPollutantUseCase.class.getName());

    public DefaultChangeDischargedPollutantUseCase(DischargedPollutantPlacer dischargedPollutantPlacer,
                                                   DischargedPollutantDataToDischargedPollutantConverter dischargedPollutantDataToDischargedPollutantConverter) {
        this.dischargedPollutantPlacer = dischargedPollutantPlacer;
        this.dischargedPollutantDataToDischargedPollutantConverter = dischargedPollutantDataToDischargedPollutantConverter;
    }

    @Override
    public void changeDischargedPollutant(DischargedPollutantData dischargedPollutantData) throws ApplicationException {
        try {
            dischargedPollutantPlacer.saveDischargedPollutant(
                    dischargedPollutantDataToDischargedPollutantConverter
                            .convertDischargedPollutantDataToDischargedPollutant(dischargedPollutantData));
        } catch (PersistenceException exc) {
            String mes = "Unable to update data in the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }
}
