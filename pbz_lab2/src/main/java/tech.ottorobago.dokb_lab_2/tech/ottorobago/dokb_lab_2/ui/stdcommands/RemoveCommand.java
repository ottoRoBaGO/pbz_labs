package tech.ottorobago.dokb_lab_2.ui.stdcommands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.ui.Command;
import tech.ottorobago.dokb_lab_2.ui.stdcommands.controllers.RemoveCommandController;

public class RemoveCommand implements Command {
    private static final String COMMAND = "remove";
    private final String COMPANY = "company";
    private final String DISCHARGE = "discharge";
    private final String CLASSIFIED_POLLUTANT = "classified_pollutant";
    private final String DISCHARGED_POLLUTANT = "discharged_pollutant";
    private final String UNDEFINED_COMMAND = "The subcommand is undefined";
    private final String WRONG_FORMAT = "Wrong format of the command";

    private boolean isControllerInitialized = false;

    private static final Logger logger = LogManager.getLogger(ListCommand.class.getName());
    private RemoveCommandController removeCommandController;

    public RemoveCommand(RemoveCommandController removeCommandController) {
        this.removeCommandController = removeCommandController;
    }

    @Override
    public boolean isThisIt(String query) {
        return query.split(" ")[0].toLowerCase().equals(COMMAND);
    }

    @Override
    public void run(String commandString) {
        if (!isControllerInitialized) {
            removeCommandController.init();

            isControllerInitialized = true;
        }

        String[] splitQuery = commandString.split(" ");

        if (splitQuery.length == 3) {
            String subject = splitQuery[1].toLowerCase();

            try {
                int id = Integer.parseInt(splitQuery[2]);

                switch (subject) {
                    case COMPANY -> removeCommandController.removeCompany(id);
                    case DISCHARGE -> removeCommandController.removeDischarge(id);
                    case CLASSIFIED_POLLUTANT -> removeCommandController.removeClassifiedPollutant(id);
                    case DISCHARGED_POLLUTANT -> removeCommandController.removeDischargedPollutant(id);
                    default -> printUndefinedCommand();
                }
            } catch (NumberFormatException exc) {
                logger.error("Input: " + splitQuery[2], exc);

                printWrongFormat();
            }
        } else
            printWrongFormat();
    }

    private void printUndefinedCommand() {
        System.out.println(UNDEFINED_COMMAND);
    }

    private void printWrongFormat() {
        System.out.println(WRONG_FORMAT);
    }
}
