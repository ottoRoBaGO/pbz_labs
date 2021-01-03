package tech.ottorobago.dokb_lab_2.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.data.DischargedPollutantData;
import tech.ottorobago.dokb_lab_2.application.data.DischargedPollutantDataToDischargedPollutantConverter;
import tech.ottorobago.dokb_lab_2.persistence.DischargedPollutantPlacer;
import tech.ottorobago.dokb_lab_2.persistence.PersistenceException;

public class DefaultCreateDischargedPollutantUseCase implements CreateDischargedPollutantUseCase {
    private DischargedPollutantPlacer dischargedPollutantPlacer;
    private DischargedPollutantDataToDischargedPollutantConverter dischargedPollutantDataToDischargedPollutantConverter;

    private static final Logger logger = LogManager.getLogger(DefaultCreateDischargedPollutantUseCase.class.getName());

    public DefaultCreateDischargedPollutantUseCase(DischargedPollutantPlacer dischargedPollutantPlacer,
                                                   DischargedPollutantDataToDischargedPollutantConverter dischargedPollutantDataToDischargedPollutantConverter) {
        this.dischargedPollutantPlacer = dischargedPollutantPlacer;
        this.dischargedPollutantDataToDischargedPollutantConverter = dischargedPollutantDataToDischargedPollutantConverter;
    }

    @Override
    public DischargedPollutantData createDischargedPollutant(DischargedPollutantData dischargedPollutantData) throws ApplicationException {
        try {
            int newId = dischargedPollutantPlacer.createDischargedPollutant(
                    dischargedPollutantDataToDischargedPollutantConverter
                            .convertDischargedPollutantDataToDischargedPollutant(dischargedPollutantData)).getId();

            dischargedPollutantData.setId(newId);

            return dischargedPollutantData;
        } catch (PersistenceException exc) {
            String mes = "Unable to insert data into the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }
}
