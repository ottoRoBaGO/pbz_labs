package tech.ottorobago.dokb_lab_2.ui.stdcommands.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.*;
import tech.ottorobago.dokb_lab_2.ui.stdcommands.views.RemoveCommandView;

public class DefaultRemoveCommandController implements RemoveCommandController {
    private RemoveCommandView removeCommandView;

    private RemoveCompanyUseCase removeCompanyUseCase;
    private RemoveDischargeUseCase removeDischargeUseCase;
    private RemoveClassifiedPollutantUseCase removeClassifiedPollutantUseCase;
    private RemoveDischargedPollutantUseCase removeDischargedPollutantUseCase;

    private static final Logger logger = LogManager.getLogger(DefaultRemoveCommandController.class.getName());

    public DefaultRemoveCommandController(RemoveCommandView removeCommandView, RemoveCompanyUseCase removeCompanyUseCase,
                                          RemoveDischargeUseCase removeDischargeUseCase,
                                          RemoveClassifiedPollutantUseCase removeClassifiedPollutantUseCase,
                                          RemoveDischargedPollutantUseCase removeDischargedPollutantUseCase) {
        this.removeCommandView = removeCommandView;
        this.removeCompanyUseCase = removeCompanyUseCase;
        this.removeDischargeUseCase = removeDischargeUseCase;
        this.removeClassifiedPollutantUseCase = removeClassifiedPollutantUseCase;
        this.removeDischargedPollutantUseCase = removeDischargedPollutantUseCase;
    }

    @Override
    public void init() {
        removeCommandView.init();
    }

    @Override
    public void removeCompany(int id) {
        try {
            removeCompanyUseCase.removeCompany(id);

            removeCommandView.showCompanyRemoved();
        } catch (ApplicationException exc) {
            logger.error("Unable to remove the company", exc);
        }
    }

    @Override
    public void removeDischarge(int id) {
        try {
            removeDischargeUseCase.removeDischarge(id);

            removeCommandView.showDischargeRemoved();
        } catch (ApplicationException exc) {
            logger.error("Unable to remove the discharge", exc);
        }
    }

    @Override
    public void removeClassifiedPollutant(int id) {
        try {
            removeClassifiedPollutantUseCase.removeClassifiedPollutant(id);

            removeCommandView.showClassifiedPollutantRemoved();
        } catch (ApplicationException exc) {
            logger.error("Unable to remove the classified pollutant", exc);
        }
    }

    @Override
    public void removeDischargedPollutant(int id) {
        try {
            removeDischargedPollutantUseCase.removeDischargedPollutant(id);

            removeCommandView.showDischargedPollutantRemoved();
        } catch (ApplicationException exc) {
            logger.error("Unable to remove the discharged pollutant", exc);
        }
    }
}
