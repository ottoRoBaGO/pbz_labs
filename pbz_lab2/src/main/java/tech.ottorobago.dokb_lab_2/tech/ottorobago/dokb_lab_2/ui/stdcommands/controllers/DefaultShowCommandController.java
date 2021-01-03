package tech.ottorobago.dokb_lab_2.ui.stdcommands.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.ApplicationException;
import tech.ottorobago.dokb_lab_2.application.RetrieveDischargeUseCase;
import tech.ottorobago.dokb_lab_2.application.data.DischargeData;
import tech.ottorobago.dokb_lab_2.ui.stdcommands.views.ShowCommandView;

public class DefaultShowCommandController implements ShowCommandController {
    private ShowCommandView showCommandView;

    private RetrieveDischargeUseCase retrieveDischargeUseCase;

    private static final Logger logger = LogManager.getLogger(DefaultShowCommandController.class.getName());

    public DefaultShowCommandController(ShowCommandView showCommandView, RetrieveDischargeUseCase retrieveDischargeUseCase) {
        this.showCommandView = showCommandView;
        this.retrieveDischargeUseCase = retrieveDischargeUseCase;
    }

    @Override
    public void init() {
        showCommandView.init();
    }

    @Override
    public void showDischargesByCompany(int discharge) {
        try {
            DischargeData dischargeData = retrieveDischargeUseCase.retrieveDischarge(discharge);

            showCommandView.showDischarge(dischargeData);
        } catch (ApplicationException exc) {
            logger.error("Unable to retrieve discharge by id", exc);
        }
    }
}
