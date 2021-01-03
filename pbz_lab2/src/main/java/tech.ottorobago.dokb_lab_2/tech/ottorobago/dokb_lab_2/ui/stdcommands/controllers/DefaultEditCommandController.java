package tech.ottorobago.dokb_lab_2.ui.stdcommands.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.*;
import tech.ottorobago.dokb_lab_2.application.data.ClassifiedPollutantData;
import tech.ottorobago.dokb_lab_2.application.data.CompanyData;
import tech.ottorobago.dokb_lab_2.application.data.DischargeData;
import tech.ottorobago.dokb_lab_2.application.data.DischargedPollutantData;
import tech.ottorobago.dokb_lab_2.ui.stdcommands.views.EditCommandView;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//I HATE THIS CLASS
public class DefaultEditCommandController implements EditCommandController {
    private EditCommandView editCommandView;

    private ChangeCompanyUseCase changeCompanyUseCase;
    private ChangeDischargeUseCase changeDischargeUseCase;
    private ChangeClassifiedPollutantUseCase changeClassifiedPollutantUseCase;
    private ChangeDischargedPollutantUseCase changeDischargedPollutantUseCase;

    private RetrieveCompanyUseCase retrieveCompanyUseCase;
    private RetrieveDischargeUseCase retrieveDischargeUseCase;
    private RetrieveClassifiedPollutantUseCase retrieveClassifiedPollutantUseCase;
    private RetrieveDischargedPollutantUseCase retrieveDischargedPollutantUseCase;

    private SimpleDateFormat simpleDateFormat;

    private final String FINISH_WORD = "finish";
    private final String GREETINGS = " -> ";

    private final String UNDEFINED_FIELD = "You entered an unknown field's name";

    //it's better to exclude codes to their own consts (like it's done with finish) but it's just a lab, right?
    private final String COMPANY_FIELDS = "NAME - name, " + FINISH_WORD + " - to exit";;
    private final String DISCHARGE_FIELDS = "NAME - name, CMPN - company id, TRGT - used target id, DATE - date (dd/MM/yyyy),\n" +
            "DIAM - diameter, MWSP - minimal water speed, WWCN - wastewater consumption, ANGL - angle,\n" +
            "DSSF - distance to surface, DSSR - distance to shore, " + FINISH_WORD + " - to exit";
    private final String CLASSIFIED_POLLUTANT_FIELDS = "POLL - pollutant id, CMPN - company id, DNCL - danger class," +
            " LFVG - LFV group, " + FINISH_WORD + " - to exit";;
    private final String DISCHARGED_POLLUTANT_FIELDS = "DSRG - discharge id, POLL - pollutant id," +
            " BCTN - background concentration, \nCTN - concentration, MPC - MPC, NCC - NCC, " + FINISH_WORD + " - to exit";

    private static final Logger logger = LogManager.getLogger(DefaultEditCommandController.class.getName());

    public DefaultEditCommandController(EditCommandView editCommandView, ChangeCompanyUseCase changeCompanyUseCase,
                                        ChangeDischargeUseCase changeDischargeUseCase,
                                        ChangeClassifiedPollutantUseCase changeClassifiedPollutantUseCase,
                                        ChangeDischargedPollutantUseCase changeDischargedPollutantUseCase,
                                        RetrieveCompanyUseCase retrieveCompanyUseCase,
                                        RetrieveDischargeUseCase retrieveDischargeUseCase,
                                        RetrieveClassifiedPollutantUseCase retrieveClassifiedPollutantUseCase,
                                        RetrieveDischargedPollutantUseCase retrieveDischargedPollutantUseCase) {
        this.editCommandView = editCommandView;
        this.changeCompanyUseCase = changeCompanyUseCase;
        this.changeDischargeUseCase = changeDischargeUseCase;
        this.changeClassifiedPollutantUseCase = changeClassifiedPollutantUseCase;
        this.changeDischargedPollutantUseCase = changeDischargedPollutantUseCase;
        this.retrieveCompanyUseCase = retrieveCompanyUseCase;
        this.retrieveDischargeUseCase = retrieveDischargeUseCase;
        this.retrieveClassifiedPollutantUseCase = retrieveClassifiedPollutantUseCase;
        this.retrieveDischargedPollutantUseCase = retrieveDischargedPollutantUseCase;

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public void init() {
        editCommandView.init();
    }

    @Override
    public void editCompany(int id) {
        try {
            editCommandView.showMessage(COMPANY_FIELDS);

            CompanyData companyData = retrieveCompanyUseCase.retrieveCompany(id);

            showGreetings();

            String field;
            while (!(field = editCommandView.askForFieldName().toLowerCase()).equals(FINISH_WORD)) {
                if (field.equals("name")) {
                    String name = editCommandView.askForCompanyName();

                    companyData.setName(name);
                } else
                    showUndefinedField();

                showGreetings();
            }

            changeCompanyUseCase.changeCompany(companyData);

            editCommandView.showCompanyChanged();
        } catch (ApplicationException exc) {
            logger.error("Unable to change or load the company. An error has occurred in the application layer", exc);
        } catch (IOException exc) {
            logger.error("Unable to read field's name", exc);
        }
    }

    @Override
    public void editDischarge(int id) {
        try {
            editCommandView.showMessage(DISCHARGE_FIELDS);

            DischargeData dischargeData = retrieveDischargeUseCase.retrieveDischarge(id);

            showGreetings();

            String field;
            while (!(field = editCommandView.askForFieldName().toLowerCase()).equals(FINISH_WORD)) {
                handleDischargeEditing(dischargeData, field);

                showGreetings();
            }

            changeDischargeUseCase.changeDischarge(dischargeData);

            editCommandView.showDischargeChanged();
        } catch (ApplicationException exc) {
            logger.error("Unable to change or load the discharge. An error has occurred in the application layer", exc);
        } catch (IOException exc) {
            logger.error("Unable to read field's name", exc);
        }
    }

    private void handleDischargeEditing(DischargeData dischargeData, String field) throws IOException {
        //it looks just great
        //but i'm a slacker so i won't split it
        try {
            switch (field) {
                case "name" -> {
                    String name = editCommandView.askForDischargeName();
                    dischargeData.setName(name);
                }
                case "cmpn" -> {
                    int company = Integer.parseInt(editCommandView.askForDischargeCompany());
                    dischargeData.setCompany(company);
                }
                case "trgt" -> {
                    int target = Integer.parseInt(editCommandView.askForDischargeUsedTarget());
                    dischargeData.setUsedTarget(target);
                }
                case "date" -> {
                    try {
                        Date date = simpleDateFormat.parse(editCommandView.askForDischargeDate());

                        dischargeData.setDate(date);
                    } catch (ParseException exc) {
                        logger.error("Unable to parse the date", exc);
                    }
                }
                case "diam" -> {
                    double diameter = Double.parseDouble(editCommandView.askForDischargeDiameter());
                    dischargeData.setDiameter(diameter);
                }
                case "mwsp" -> {
                    double minimalWaterSpeed = Double.parseDouble(editCommandView.askForDischargeMinimalWaterSpeed());
                    dischargeData.setMinimalWaterSpeed(minimalWaterSpeed);
                }
                case "wwcn" -> {
                    double wastewaterConsumption = Double.parseDouble(editCommandView.askForDischargeWastewaterConsumption());
                    dischargeData.setWastewaterConsumption(wastewaterConsumption);
                }
                case "angl" -> {
                    double angle = Double.parseDouble(editCommandView.askForDischargeAngle());
                    dischargeData.setAngle(angle);
                }
                case "dssf" -> {
                    double distanceToSurface = Double.parseDouble(editCommandView.askForDischargeDistanceToSurface());
                    dischargeData.setDistanceToSurface(distanceToSurface);
                }
                case "dssr" -> {
                    double distanceToShore = Double.parseDouble(editCommandView.askForDischargeDistanceToShore());
                    dischargeData.setDistanceToShore(distanceToShore);
                }
                default -> showUndefinedField();
            }
        } catch (NumberFormatException exc) {
            logger.error("Unable to parse the integer", exc);
        }

        //ahahaha, Jesus, what have i written here??
    }

    @Override
    public void editClassifiedPollutant(int id) {
        try {
            editCommandView.showMessage(CLASSIFIED_POLLUTANT_FIELDS);

            ClassifiedPollutantData classifiedPollutantData
                    = retrieveClassifiedPollutantUseCase.retrieveClassifiedPollutant(id);

            showGreetings();

            String field;
            while (!(field = editCommandView.askForFieldName().toLowerCase()).equals(FINISH_WORD)) {
                handleClassifiedPollutantEditing(classifiedPollutantData, field);

                showGreetings();
            }

            changeClassifiedPollutantUseCase.changeClassifiedPollutant(classifiedPollutantData);

            editCommandView.showClassifiedPollutantChanged();
        } catch (ApplicationException exc) {
            logger.error("Unable to change or load the discharge. An error has occurred in the application layer", exc);
        } catch (IOException exc) {
            logger.error("Unable to read field's name", exc);
        }
    }

    private void handleClassifiedPollutantEditing(ClassifiedPollutantData classifiedPollutantData, String field)
        throws IOException {
        try {
            switch (field) {
                case "cmpn" -> {
                    int company = Integer.parseInt(editCommandView.askForClassifiedPollutantCompany());
                    classifiedPollutantData.setCompany(company);
                }
                case "poll" -> {
                    int pollutant = Integer.parseInt(editCommandView.askForClassifiedPollutantPollutant());
                    classifiedPollutantData.setPollutant(pollutant);
                }
                case "dncl" -> {
                    String dangerClass = editCommandView.askForClassifiedPollutantDangerClass();
                    classifiedPollutantData.setDangerClass(dangerClass);
                }
                case "lvfg" -> {
                    String lfvGroup = editCommandView.askForClassifiedPollutantLfvGroup();
                    classifiedPollutantData.setLfvGroup(lfvGroup);
                }
                default -> showUndefinedField();
            }
        } catch (NumberFormatException exc) {
            logger.error("Unable to parse the integer", exc);
        }
    }

    @Override
    public void editDischargedPollutant(int id) {
        try {
            editCommandView.showMessage(DISCHARGED_POLLUTANT_FIELDS);

            DischargedPollutantData dischargedPollutantData
                    = retrieveDischargedPollutantUseCase.retrieveDischargedPollutant(id);

            showGreetings();

            String field;
            while (!(field = editCommandView.askForFieldName().toLowerCase()).equals(FINISH_WORD)) {
                handleDischargedPollutantEditing(dischargedPollutantData, field);

                showGreetings();
            }

            changeDischargedPollutantUseCase.changeDischargedPollutant(dischargedPollutantData);

            editCommandView.showClassifiedPollutantChanged();
        } catch (ApplicationException exc) {
            logger.error("Unable to change or load the discharged pollutant. An error has occurred in the application layer", exc);
        } catch (IOException exc) {
            logger.error("Unable to read field's name", exc);
        }
    }

    private void handleDischargedPollutantEditing(DischargedPollutantData dischargedPollutantData, String field)
            throws IOException {
        try {
            switch (field) {
                case "dsrg" -> {
                    int discharge = Integer.parseInt(editCommandView.askForDischargedPollutantDischarge());
                    dischargedPollutantData.setDischarge(discharge);
                }
                case "poll" -> {
                    int pollutant = Integer.parseInt(editCommandView.askForClassifiedPollutantPollutant());
                    dischargedPollutantData.setPollutant(pollutant);
                }
                case "bctn" -> {
                    double backgroundConcentration
                            = Double.parseDouble(editCommandView.askForDischargedPollutantBackgroundConcentration());
                    dischargedPollutantData.setBackgroundConcentration(backgroundConcentration);
                }
                case "ctn" -> {
                    double concentration = Double.parseDouble(editCommandView.askForDischargedPollutantConcentration());
                    dischargedPollutantData.setConcentration(concentration);
                }
                case "mpc" -> {
                    double mpc = Double.parseDouble(editCommandView.askForDischargedPollutantMpc());
                    dischargedPollutantData.setMpc(mpc);
                }
                case "ncc" -> {
                    double ncc = Double.parseDouble(editCommandView.askForDischargedPollutantNcc());
                    dischargedPollutantData.setNcc(ncc);
                }
                default -> showUndefinedField();
            }
        } catch (NumberFormatException exc) {
            logger.error("Unable to parse the integer", exc);
        }
    }

    private void showUndefinedField() {
        editCommandView.showMessage(UNDEFINED_FIELD);
    }

    private void showGreetings() {
        editCommandView.showInlineMessage(GREETINGS);
    }
}
