package tech.ottorobago.dokb_lab_2.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.data.DischargedPollutantData;
import tech.ottorobago.dokb_lab_2.application.data.factories.DischargedPollutantDataFactory;
import tech.ottorobago.dokb_lab_2.domain.DischargedPollutant;
import tech.ottorobago.dokb_lab_2.persistence.DischargedPollutantRetriever;
import tech.ottorobago.dokb_lab_2.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;

public class DefaultRetrieveDischargedPollutantUseCase implements RetrieveDischargedPollutantUseCase {
    private DischargedPollutantRetriever dischargedPollutantRetriever;
    private DischargedPollutantDataFactory dischargedPollutantDataFactory;

    private static final Logger logger = LogManager.getLogger(DefaultRetrieveClassifiedPollutantUseCase.class.getName());

    public DefaultRetrieveDischargedPollutantUseCase(DischargedPollutantRetriever dischargedPollutantRetriever,
                                                     DischargedPollutantDataFactory dischargedPollutantDataFactory) {
        this.dischargedPollutantRetriever = dischargedPollutantRetriever;
        this.dischargedPollutantDataFactory = dischargedPollutantDataFactory;
    }

    @Override
    public DischargedPollutantData retrieveDischargedPollutant(int id) throws ApplicationException {
        try {
            DischargedPollutant dischargedPollutant = dischargedPollutantRetriever.loadDischargedPollutant(id);

            return convertClassifiedPollutantToClassifiedPollutantData(dischargedPollutant);
        } catch (PersistenceException exc) {
            String mes = "Unable to take the data from the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }

    @Override
    public List<DischargedPollutantData> retrieveDischargedPollutantsByDischarge(int discharge) throws ApplicationException {
        try {
            List<DischargedPollutant> dischargedPollutants =
                    dischargedPollutantRetriever.loadDischargedPollutantByDischarge(discharge);

            List<DischargedPollutantData> result = new ArrayList<>();

            for (DischargedPollutant dischargedPollutant : dischargedPollutants)
                result.add(convertClassifiedPollutantToClassifiedPollutantData(dischargedPollutant));

            return result;
        } catch (PersistenceException exc) {
            String mes = "Unable to take the data from the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }

    private DischargedPollutantData convertClassifiedPollutantToClassifiedPollutantData(
            DischargedPollutant dischargedPollutant) {
        return dischargedPollutantDataFactory.createDischargedPollutantData(dischargedPollutant.getId(),
                dischargedPollutant.getDischarge(), dischargedPollutant.getPollutant(),
                dischargedPollutant.getConcentration(), dischargedPollutant.getNcc(),
                dischargedPollutant.getBackgroundConcentration(), dischargedPollutant.getMpc());
    }
}
