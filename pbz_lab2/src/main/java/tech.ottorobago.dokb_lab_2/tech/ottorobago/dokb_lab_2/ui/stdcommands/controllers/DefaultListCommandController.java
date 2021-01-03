package tech.ottorobago.dokb_lab_2.ui.stdcommands.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.*;
import tech.ottorobago.dokb_lab_2.application.data.*;
import tech.ottorobago.dokb_lab_2.ui.stdcommands.views.ListCommandView;

import java.util.Date;
import java.util.List;

public class DefaultListCommandController implements ListCommandController {
    private ListCommandView listCommandView;

    private RetrieveTargetUseCase retrieveTargetUseCase;
    private RetrievePollutantUseCase retrievePollutantUseCase;
    private RetrieveCompanyUseCase retrieveCompanyUseCase;
    private RetrieveDischargeUseCase retrieveDischargeUseCase;
    private RetrieveClassifiedPollutantUseCase retrieveClassifiedPollutantUseCase;
    private RetrieveDischargedPollutantUseCase retrieveDischargedPollutantUseCase;

    private static final Logger logger = LogManager.getLogger(DefaultListCommandController.class.getName());

    public DefaultListCommandController(ListCommandView listCommandView, RetrieveTargetUseCase retrieveTargetUseCase,
                                        RetrievePollutantUseCase retrievePollutantUseCase,
                                        RetrieveCompanyUseCase retrieveCompanyUseCase,
                                        RetrieveDischargeUseCase retrieveDischargeUseCase,
                                        RetrieveClassifiedPollutantUseCase retrieveClassifiedPollutantUseCase,
                                        RetrieveDischargedPollutantUseCase retrieveDischargedPollutantUseCase) {
        this.listCommandView = listCommandView;
        this.retrieveTargetUseCase = retrieveTargetUseCase;
        this.retrievePollutantUseCase = retrievePollutantUseCase;
        this.retrieveCompanyUseCase = retrieveCompanyUseCase;
        this.retrieveDischargeUseCase = retrieveDischargeUseCase;
        this.retrieveClassifiedPollutantUseCase = retrieveClassifiedPollutantUseCase;
        this.retrieveDischargedPollutantUseCase = retrieveDischargedPollutantUseCase;
    }

    @Override
    public void init() {
        listCommandView.init();
    }

    @Override
    public void listTargets() {
        try {
            List<TargetData> targets = retrieveTargetUseCase.retrieveAllTargets();

            for (TargetData target : targets)
                listCommandView.showTarget(target);
        } catch (ApplicationException exc) {
            logger.error("Unable to retrieve targets", exc);
        }
    }

    @Override
    public void listPollutants() {
        try {
            List<PollutantData> pollutants = retrievePollutantUseCase.retrieveAllPollutants();

            for (PollutantData pollutant : pollutants)
                listCommandView.showPollutant(pollutant);
        } catch (ApplicationException exc) {
            logger.error("Unable to retrieve pollutants", exc);
        }
    }

    @Override
    public void listCompanies() {
        try {
            List<CompanyData> companies = retrieveCompanyUseCase.retrieveAllCompanies();

            for (CompanyData company : companies)
                listCommandView.showCompany(company);
        } catch (ApplicationException exc) {
            logger.error("Unable to retrieve companies", exc);
        }
    }

    @Override
    public void listDischargesByCompany(int company) {
        try {
            List<DischargeData> discharges = retrieveDischargeUseCase.retrieveDischargesByCompany(company);

            showDischarges(discharges);
        } catch (ApplicationException exc) {
            logger.error("Unable to retrieve discharges by company", exc);
        }
    }

    @Override
    public void listDischargesByCompanyAndTime(int company, Date since, Date until) {
        try {
            List<DischargeData> discharges = retrieveDischargeUseCase.
                    retrieveDischargesByCompanyAndTime(company, since, until);

            showDischarges(discharges);
        } catch (ApplicationException exc) {
            logger.error("Unable to retrieve discharges by company and time", exc);
        }
    }

    @Override
    public void listClassifiedPollutantsByCompany(int company) {
        try {
            List<ClassifiedPollutantData> classifiedPollutants
                    = retrieveClassifiedPollutantUseCase.retrieveClassifiedPollutantsByCompany(company);

            for (ClassifiedPollutantData classifiedPollutantData : classifiedPollutants)
                listCommandView.showClassifiedPollutant(classifiedPollutantData);

        } catch (ApplicationException exc) {
            logger.error("Unable to retrieve classified pollutants by company", exc);
        }
    }

    @Override
    public void listDischargedPollutantsByDischarge(int discharge) {
        try {
            List<DischargedPollutantData> dischargedPollutants
                    = retrieveDischargedPollutantUseCase.retrieveDischargedPollutantsByDischarge(discharge);

            for (DischargedPollutantData dischargedPollutant : dischargedPollutants)
                listCommandView.showDischargedPollutant(dischargedPollutant);

        } catch (ApplicationException exc) {
            logger.error("Unable to retrieve discharged pollutants by discharge", exc);
        }
    }

    private void showDischarges(List<DischargeData> discharges) {
        for (DischargeData discharge : discharges)
            listCommandView.showDischarge(discharge);
    }
}
