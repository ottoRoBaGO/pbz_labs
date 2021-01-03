package tech.ottorobago.dokb_lab_2.ui.stdcommands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.ui.Command;
import tech.ottorobago.dokb_lab_2.ui.stdcommands.controllers.ListCommandController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListCommand implements Command {
    private final String COMMAND = "list";
    private final String TARGETS = "targets";
    private final String POLLUTANTS = "pollutants";
    private final String COMPANIES = "companies";
    private final String DISCHARGES = "discharges";
    private final String CLASSIFIED_POLLUTANTS = "classified_pollutants";
    private final String DISCHARGED_POLLUTANTS = "discharged_pollutants";
    private final String UNDEFINED_COMMAND = "The subcommand is undefined";
    private final String WRONG_FORMAT = "Wrong format of the command";

    private boolean isControllerInitialized = false;

    private static final Logger logger = LogManager.getLogger(ListCommand.class.getName());
    private ListCommandController listCommandController;

    public ListCommand(ListCommandController listCommandController) {
        this.listCommandController = listCommandController;
    }

    @Override
    public boolean isThisIt(String query) {
        return query.split(" ")[0].toLowerCase().equals(COMMAND);
    }

    @Override
    public void run(String commandString) {
        if (!isControllerInitialized) {
            listCommandController.init();

            isControllerInitialized = true;
        }

        String[] splitQuery = commandString.split(" ");

        if (splitQuery.length > 1) {
            String subject = splitQuery[1].toLowerCase();

            switch (subject) {
                case TARGETS -> listCommandController.listTargets();
                case POLLUTANTS -> listCommandController.listPollutants();
                case COMPANIES -> listCommandController.listCompanies();
                case DISCHARGES -> handleDischarges(splitQuery);
                case CLASSIFIED_POLLUTANTS -> handleClassifiedPollutants(splitQuery);
                case DISCHARGED_POLLUTANTS -> handleDischargedPollutant(splitQuery);
                default -> printUndefinedCommand();
            }
        } else
            printUndefinedCommand();
    }

    private void handleClassifiedPollutants(String[] splitQuery) {
        if (splitQuery.length == 3) {
            try {
                int company = Integer.parseInt(splitQuery[2]);

                listCommandController.listClassifiedPollutantsByCompany(company);
            } catch (NumberFormatException exc) {
                logger.error("Input: " + splitQuery[2], exc);

                printWrongFormat();
            }
        } else
            printWrongFormat();
    }

    private void handleDischargedPollutant(String[] splitQuery) {
        if (splitQuery.length == 3) {
            try {
                int discharge = Integer.parseInt(splitQuery[2]);

                listCommandController.listDischargedPollutantsByDischarge(discharge);
            } catch (NumberFormatException exc) {
                logger.error("Input: " + splitQuery[2], exc);

                printWrongFormat();
            }
        } else
            printWrongFormat();
    }

    private void handleDischarges(String[] splitQuery) {
        if (splitQuery.length >= 3) {
            //by company

            try {
                int company = Integer.parseInt(splitQuery[2]);

                if (splitQuery.length == 5) {
                    //by company + time

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

                    try {
                        Date since = simpleDateFormat.parse(splitQuery[3]);
                        Date until = simpleDateFormat.parse(splitQuery[4]);

                        listCommandController.listDischargesByCompanyAndTime(company, since, until);
                    } catch (ParseException exc) {
                        logger.error("Input: " + splitQuery[3] + ", " + splitQuery[4], exc);
                        printWrongFormat();
                    }
                } else if (splitQuery.length == 3)
                    listCommandController.listDischargesByCompany(company);
                else
                    printWrongFormat();
            } catch (NumberFormatException exc) {
                logger.error("Input: " + splitQuery[2], exc);
                printWrongFormat();
            }
        }
    }

    private void printUndefinedCommand() {
        System.out.println(UNDEFINED_COMMAND);
    }

    private void printWrongFormat() {
        System.out.println(WRONG_FORMAT);
    }
}
