package tech.ottorobago.dokb_lab_2.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.data.PollutantData;
import tech.ottorobago.dokb_lab_2.application.data.factories.PollutantDataFactory;
import tech.ottorobago.dokb_lab_2.domain.Pollutant;
import tech.ottorobago.dokb_lab_2.persistence.PersistenceException;
import tech.ottorobago.dokb_lab_2.persistence.PollutantRetriever;

import java.util.ArrayList;
import java.util.List;

public class DefaultRetrievePollutantUseCase implements RetrievePollutantUseCase {
    private PollutantRetriever pollutantRetriever;
    private PollutantDataFactory pollutantDataFactory;

    private static final Logger logger = LogManager.getLogger(DefaultRetrievePollutantUseCase.class.getName());

    public DefaultRetrievePollutantUseCase(PollutantRetriever pollutantRetriever,
                                           PollutantDataFactory pollutantDataFactory) {
        this.pollutantRetriever = pollutantRetriever;
        this.pollutantDataFactory = pollutantDataFactory;
    }

    @Override
    public List<PollutantData> retrieveAllPollutants() throws ApplicationException {
        try {
            List<Pollutant> pollutants = pollutantRetriever.loadAllPollutants();

            List<PollutantData> result = new ArrayList<>();

            for (Pollutant pollutant : pollutants)
                result.add(convertPollutantToPollutantData(pollutant));

            return result;
        } catch (PersistenceException exc) {
            String mes = "Unable to take the data from the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }

    private PollutantData convertPollutantToPollutantData(Pollutant pollutant) {
        return pollutantDataFactory.createPollutantData(pollutant.getId(), pollutant.getName());
    }
}
