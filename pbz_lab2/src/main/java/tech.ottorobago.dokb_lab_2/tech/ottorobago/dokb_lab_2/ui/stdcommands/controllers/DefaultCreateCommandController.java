package tech.ottorobago.dokb_lab_2.ui.stdcommands.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.*;
import tech.ottorobago.dokb_lab_2.application.data.factories.ClassifiedPollutantDataFactory;
import tech.ottorobago.dokb_lab_2.application.data.factories.CompanyDataFactory;
import tech.ottorobago.dokb_lab_2.application.data.factories.DischargeDataFactory;
import tech.ottorobago.dokb_lab_2.application.data.factories.DischargedPollutantDataFactory;
import tech.ottorobago.dokb_lab_2.ui.stdcommands.views.CreateCommandView;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DefaultCreateCommandController implements CreateCommandController {
    private CreateCommandView createCommandView;

    private CreateCompanyUseCase createCompanyUseCase;
    private CreateDischargeUseCase createDischargeUseCase;
    private CreateClassifiedPollutantUseCase createClassifiedPollutantUseCase;
    private CreateDischargedPollutantUseCase createDischargedPollutantUseCase;

    private CompanyDataFactory companyDataFactory;
    private DischargeDataFactory dischargeDataFactory;
    private ClassifiedPollutantDataFactory classifiedPollutantDataFactory;
    private DischargedPollutantDataFactory dischargedPollutantDataFactory;

    private static final Logger logger = LogManager.getLogger(DefaultRemoveCommandController.class.getName());

    private SimpleDateFormat simpleDateFormat;

    public DefaultCreateCommandController(CreateCommandView createCommandView, CreateCompanyUseCase createCompanyUseCase,
                                          CreateDischargeUseCase createDischargeUseCase,
                                          CreateClassifiedPollutantUseCase createClassifiedPollutantUseCase,
                                          CreateDischargedPollutantUseCase createDischargedPollutantUseCase,
                                          CompanyDataFactory companyDataFactory, DischargeDataFactory dischargeDataFactory,
                                          ClassifiedPollutantDataFactory classifiedPollutantDataFactory,
                                          DischargedPollutantDataFactory dischargedPollutantDataFactory) {
        this.createCommandView = createCommandView;
        this.createCompanyUseCase = createCompanyUseCase;
        this.createDischargeUseCase = createDischargeUseCase;
        this.createClassifiedPollutantUseCase = createClassifiedPollutantUseCase;
        this.createDischargedPollutantUseCase = createDischargedPollutantUseCase;
        this.companyDataFactory = companyDataFactory;
        this.dischargeDataFactory = dischargeDataFactory;
        this.classifiedPollutantDataFactory = classifiedPollutantDataFactory;
        this.dischargedPollutantDataFactory = dischargedPollutantDataFactory;
    }

    @Override
    public void init() {
        createCommandView.init();

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public void createCompany() {
        try {
            String name = createCommandView.askForCompanyName();

            createCompanyUseCase.createCompany(companyDataFactory.createCompanyData(0, name));

            createCommandView.showCompanyCreated();
        } catch (IOException exc) {
            logger.error("Unable to create a company: an I/O error", exc);
        } catch (ApplicationException exc) {
            logger.error("Unable to create a company. Application layer error", exc);
        }
    }

    @Override
    public void createDischarge() {
        try {
            int company = Integer.parseInt(createCommandView.askForDischargeCompany());
            int usedTarget = Integer.parseInt(createCommandView.askForDischargeUsedTarget());
            String name = createCommandView.askForCompanyName();
            Date date = simpleDateFormat.parse(createCommandView.askForDischargeDate());
            double diameter = Double.parseDouble(createCommandView.askForDischargeDiameter());
            double minimalWaterSpeed = Double.parseDouble(createCommandView.askForDischargeMinimalWaterSpeed());
            double wastewaterConsumption = Double.parseDouble(createCommandView.askForDischargeWastewaterConsumption());
            double angle = Double.parseDouble(createCommandView.askForDischargeAngle());
            double distanceToSurface = Double.parseDouble(createCommandView.askForDischargeDistanceToSurface());
            double distanceToShore = Double.parseDouble(createCommandView.askForDischargeDistanceToShore());

            createDischargeUseCase.createDischarge(dischargeDataFactory.createDischargeData(
                    0, company, usedTarget, name, date, diameter, minimalWaterSpeed, wastewaterConsumption, angle,
                    distanceToSurface, distanceToShore
            ));

            createCommandView.showDischargeCreated();
        } catch (IOException exc) {
            logger.error("Unable to create a company: an I/O error", exc);
        } catch (ApplicationException exc) {
            logger.error("Unable to create a company. Application layer error", exc);
        } catch (NumberFormatException exc) {
            logger.error("Unable to parse the number", exc);
        } catch (ParseException exc) {
            logger.error("Unable to parse the date (format dd/MM/yyyy)", exc);
        }
    }

    @Override
    public void createClassifiedPollutant() {
        try {
            int company = Integer.parseInt(createCommandView.askForClassifiedPollutantCompany());
            int pollutant = Integer.parseInt(createCommandView.askForClassifiedPollutantPollutant());
            String dangerClass = createCommandView.askForClassifiedPollutantDangerClass();
            String lfvGroup = createCommandView.askForClassifiedPollutantLfvGroup();

            createClassifiedPollutantUseCase.createClassifiedPollutant(
                    classifiedPollutantDataFactory.createClassifiedPollutantData(
                    0, company, pollutant, dangerClass, lfvGroup
            ));

            createCommandView.showClassifiedPollutantCreated();
        } catch (IOException exc) {
            logger.error("Unable to create a classified pollutant: an I/O error", exc);
        } catch (ApplicationException exc) {
            logger.error("Unable to create a classified pollutant. Application layer error", exc);
        } catch (NumberFormatException exc) {
            logger.error("Unable to parse the number", exc);
        }
    }

    @Override
    public void createDischargedPollutant() {
        try {
            int discharge = Integer.parseInt(createCommandView.askForDischargedPollutantDischarge());
            int pollutant = Integer.parseInt(createCommandView.askForDischargedPollutantPollutant());
            double concentration = Double.parseDouble(createCommandView.askForDischargedPollutantConcentration());
            double ncc = Double.parseDouble(createCommandView.askForDischargedPollutantNcc());
            double backgroundConcentration = Double.parseDouble(createCommandView.askForDischargedPollutantBackgroundConcentration());
            double mpc = Double.parseDouble(createCommandView.askForDischargedPollutantMpc());

            createDischargedPollutantUseCase.createDischargedPollutant(
                    dischargedPollutantDataFactory.createDischargedPollutantData(
                            0, discharge, pollutant, concentration, ncc, backgroundConcentration, mpc
            ));

            createCommandView.showDischargedPollutantCreated();
        } catch (IOException exc) {
            logger.error("Unable to create a classified pollutant: an I/O error", exc);
        } catch (ApplicationException exc) {
            logger.error("Unable to create a classified pollutant. Application layer error", exc);
        } catch (NumberFormatException exc) {
            logger.error("Unable to parse the number", exc);
        }
    }
}
