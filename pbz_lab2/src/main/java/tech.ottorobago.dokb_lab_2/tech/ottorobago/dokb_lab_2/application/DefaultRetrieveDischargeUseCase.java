package tech.ottorobago.dokb_lab_2.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.data.DischargeData;
import tech.ottorobago.dokb_lab_2.application.data.factories.DischargeDataFactory;
import tech.ottorobago.dokb_lab_2.domain.Discharge;
import tech.ottorobago.dokb_lab_2.persistence.DischargeRetriever;
import tech.ottorobago.dokb_lab_2.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DefaultRetrieveDischargeUseCase implements RetrieveDischargeUseCase {
    private DischargeRetriever dischargeRetriever;
    private DischargeDataFactory dischargeDataFactory;

    private static final Logger logger = LogManager.getLogger(DefaultRetrieveDischargeUseCase.class.getName());

    public DefaultRetrieveDischargeUseCase(DischargeRetriever dischargeRetriever,
                                           DischargeDataFactory dischargeDataFactory) {
        this.dischargeRetriever = dischargeRetriever;
        this.dischargeDataFactory = dischargeDataFactory;
    }

    @Override
    public DischargeData retrieveDischarge(int id) throws ApplicationException {
        try {
            Discharge discharge = dischargeRetriever.loadDischarge(id);

            return convertDischargeToDischargeData(discharge);
        } catch (PersistenceException exc) {
            String mes = "Unable to take the data from the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }

    @Override
    public List<DischargeData> retrieveDischargesByCompany(int company) throws ApplicationException {
        try {
            List<Discharge> discharges = dischargeRetriever.loadDischargesByCompany(company);

            List<DischargeData> result = new ArrayList<>();

            for (Discharge discharge : discharges)
                result.add(convertDischargeToDischargeData(discharge));

            return result;
        } catch (PersistenceException exc) {
            String mes = "Unable to take the data from the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }

    @Override
    public List<DischargeData> retrieveDischargesByCompanyAndTime(int company, Date since, Date until) throws ApplicationException {
        try {
            List<Discharge> discharges = dischargeRetriever.loadDischargesByCompanyAndTime(company, since, until);

            //a small duplicate but i don't care
            List<DischargeData> result = new ArrayList<>();

            for (Discharge discharge : discharges)
                result.add(convertDischargeToDischargeData(discharge));

            return result;
        } catch (PersistenceException exc) {
            String mes = "Unable to take the data from the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }

    private DischargeData convertDischargeToDischargeData(Discharge discharge) {
        return dischargeDataFactory.createDischargeData(discharge.getId(), discharge.getCompany(),
                discharge.getUsedTarget(), discharge.getName(), discharge.getDate(), discharge.getDiameter(),
                discharge.getMinimalWaterSpeed(), discharge.getWastewaterConsumption(), discharge.getAngle(),
                discharge.getDistanceToSurface(), discharge.getDistanceToShore());
    }
}
