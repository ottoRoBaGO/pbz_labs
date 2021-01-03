package tech.ottorobago.dokb_lab_2.ui.stdcommands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.ui.Command;
import tech.ottorobago.dokb_lab_2.ui.stdcommands.controllers.CreateCommandController;

public class CreateCommand implements Command {
    private static final String COMMAND = "create";
    private final String COMPANY = "company";
    private final String DISCHARGE = "discharge";
    private final String CLASSIFIED_POLLUTANT = "classified_pollutant";
    private final String DISCHARGED_POLLUTANT = "discharged_pollutant";
    private final String UNDEFINED_COMMAND = "The subcommand is undefined";
    private final String WRONG_FORMAT = "Wrong format of the command";

    private boolean isControllerInitialized = false;

    private static final Logger logger = LogManager.getLogger(CreateCommand.class.getName());
    private CreateCommandController createCommandController;

    public CreateCommand(CreateCommandController createCommandController) {
        this.createCommandController = createCommandController;
    }

    @Override
    public boolean isThisIt(String query) {
        return query.split(" ")[0].toLowerCase().equals(COMMAND);
    }

    @Override
    public void run(String commandString) {
        if (!isControllerInitialized) {
            createCommandController.init();

            isControllerInitialized = true;
        }

        String[] splitQuery = commandString.split(" ");

        if (splitQuery.length == 2) {
            switch (splitQuery[1]) {
                case COMPANY -> createCommandController.createCompany();
                case DISCHARGE -> createCommandController.createDischarge();
                case CLASSIFIED_POLLUTANT -> createCommandController.createClassifiedPollutant();
                case DISCHARGED_POLLUTANT -> createCommandController.createDischargedPollutant();
                default -> printUndefinedCommand();
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
