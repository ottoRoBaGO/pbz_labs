package tech.ottorobago.dokb_lab_2.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.data.DischargeData;
import tech.ottorobago.dokb_lab_2.application.data.DischargeDataToDischargeConverter;
import tech.ottorobago.dokb_lab_2.persistence.DischargePlacer;
import tech.ottorobago.dokb_lab_2.persistence.PersistenceException;

public class DefaultCreateDischargeUseCase implements CreateDischargeUseCase {
    private DischargePlacer dischargePlacer;
    private DischargeDataToDischargeConverter dischargeDataToDischargeConverter;

    private static final Logger logger = LogManager.getLogger(DefaultCreateDischargeUseCase.class.getName());

    public DefaultCreateDischargeUseCase(DischargePlacer dischargePlacer,
                                         DischargeDataToDischargeConverter dischargeDataToDischargeConverter) {
        this.dischargePlacer = dischargePlacer;
        this.dischargeDataToDischargeConverter = dischargeDataToDischargeConverter;
    }

    @Override
    public DischargeData createDischarge(DischargeData dischargeData) throws ApplicationException {
        try {
            int newId = dischargePlacer.createDischarge(
                    dischargeDataToDischargeConverter
                            .convertDischargeDataToDischarge(dischargeData)).getId();

            dischargeData.setId(newId);

            return dischargeData;
        } catch (PersistenceException exc) {
            String mes = "Unable to insert data into the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }
}
