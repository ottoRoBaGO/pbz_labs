package tech.ottorobago.dokb_lab_2.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.data.ClassifiedPollutantData;
import tech.ottorobago.dokb_lab_2.application.data.factories.ClassifiedPollutantDataFactory;
import tech.ottorobago.dokb_lab_2.domain.ClassifiedPollutant;
import tech.ottorobago.dokb_lab_2.persistence.ClassifiedPollutantRetriever;
import tech.ottorobago.dokb_lab_2.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;

public class DefaultRetrieveClassifiedPollutantUseCase implements RetrieveClassifiedPollutantUseCase {
    private ClassifiedPollutantRetriever classifiedPollutantRetriever;
    private ClassifiedPollutantDataFactory classifiedPollutantDataFactory;

    private static final Logger logger = LogManager.getLogger(DefaultRetrieveClassifiedPollutantUseCase.class.getName());

    public DefaultRetrieveClassifiedPollutantUseCase(ClassifiedPollutantRetriever classifiedPollutantRetriever,
                                                     ClassifiedPollutantDataFactory classifiedPollutantDataFactory) {
        this.classifiedPollutantRetriever = classifiedPollutantRetriever;
        this.classifiedPollutantDataFactory = classifiedPollutantDataFactory;
    }

    @Override
    public ClassifiedPollutantData retrieveClassifiedPollutant(int id) throws ApplicationException {
        try {
            ClassifiedPollutant classifiedPollutant = classifiedPollutantRetriever.loadClassifiedPollutant(id);

            return convertClassifiedPollutantToClassifiedPollutantData(classifiedPollutant);
        } catch (PersistenceException exc) {
            String mes = "Unable to take the data from the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }

    @Override
    public List<ClassifiedPollutantData> retrieveClassifiedPollutantsByCompany(int company) throws ApplicationException {
        try {
            List<ClassifiedPollutant> classifiedPollutants =
                    classifiedPollutantRetriever.loadClassifiedPollutantsByCompany(company);

            List<ClassifiedPollutantData> result = new ArrayList<>();

            for (ClassifiedPollutant classifiedPollutant : classifiedPollutants)
                result.add(convertClassifiedPollutantToClassifiedPollutantData(classifiedPollutant));

            return result;
        } catch (PersistenceException exc) {
            String mes = "Unable to take the data from the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }

    private ClassifiedPollutantData convertClassifiedPollutantToClassifiedPollutantData(
            ClassifiedPollutant classifiedPollutant) {
        return classifiedPollutantDataFactory.createClassifiedPollutantData(classifiedPollutant.getId(),
                classifiedPollutant.getCompany(), classifiedPollutant.getPollutant(),
                classifiedPollutant.getDangerClass(), classifiedPollutant.getLfvGroup());
    }
}
