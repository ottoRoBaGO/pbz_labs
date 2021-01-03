package tech.ottorobago.dokb_lab_2.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.data.TargetData;
import tech.ottorobago.dokb_lab_2.application.data.factories.TargetDataFactory;
import tech.ottorobago.dokb_lab_2.domain.Target;
import tech.ottorobago.dokb_lab_2.persistence.PersistenceException;
import tech.ottorobago.dokb_lab_2.persistence.TargetRetriever;

import java.util.ArrayList;
import java.util.List;

public class DefaultRetrieveTargetUseCase implements RetrieveTargetUseCase {
    private TargetRetriever targetRetriever;
    private TargetDataFactory targetDataFactory;

    private Logger logger = LogManager.getLogger(DefaultRetrieveTargetUseCase.class.getName());

    public DefaultRetrieveTargetUseCase(TargetRetriever targetRetriever, TargetDataFactory targetDataFactory) {
        this.targetRetriever = targetRetriever;
        this.targetDataFactory = targetDataFactory;
    }

    @Override
    public List<TargetData> retrieveAllTargets() throws ApplicationException {
        try {
            List<Target> targets = targetRetriever.loadAllTargets();

            List<TargetData> result = new ArrayList<>();

            for (Target target : targets)
                result.add(convertTargetToTargetData(target));

            return result;
        } catch (PersistenceException exc) {
            String mes = "Unable to take data from persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }

    private TargetData convertTargetToTargetData(Target target) {
        return targetDataFactory.createTargetData(target.getId(), target.getName(), target.getDistance());
    }
}
