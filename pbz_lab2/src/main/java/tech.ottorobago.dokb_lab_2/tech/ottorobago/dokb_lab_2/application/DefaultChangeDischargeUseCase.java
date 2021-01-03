package tech.ottorobago.dokb_lab_2.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.data.DischargeData;
import tech.ottorobago.dokb_lab_2.application.data.DischargeDataToDischargeConverter;
import tech.ottorobago.dokb_lab_2.persistence.DischargePlacer;
import tech.ottorobago.dokb_lab_2.persistence.PersistenceException;

public class DefaultChangeDischargeUseCase implements ChangeDischargeUseCase {
    private DischargePlacer dischargePlacer;
    private DischargeDataToDischargeConverter dischargeDataToDischargeConverter;

    private static final Logger logger = LogManager.getLogger(DefaultChangeDischargeUseCase.class.getName());

    public DefaultChangeDischargeUseCase(DischargePlacer dischargePlacer,
                                         DischargeDataToDischargeConverter dischargeDataToDischargeConverter) {
        this.dischargePlacer = dischargePlacer;
        this.dischargeDataToDischargeConverter = dischargeDataToDischargeConverter;
    }

    @Override
    public void changeDischarge(DischargeData dischargeData) throws ApplicationException {
        try {
            dischargePlacer.saveDischarge(
                    dischargeDataToDischargeConverter
                            .convertDischargeDataToDischarge(dischargeData));
        } catch (PersistenceException exc) {
            String mes = "Unable to update data in the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }
}
