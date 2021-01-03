package tech.ottorobago.dokb_lab_2.ui.stdcommands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.ui.Command;
import tech.ottorobago.dokb_lab_2.ui.stdcommands.controllers.EditCommandController;

public class EditCommand implements Command {
    private static final String COMMAND = "edit";
    private final String COMPANY = "company";
    private final String DISCHARGE = "discharge";
    private final String CLASSIFIED_POLLUTANT = "classified_pollutant";
    private final String DISCHARGED_POLLUTANT = "discharged_pollutant";
    private final String UNDEFINED_COMMAND = "The subcommand is undefined";
    private final String WRONG_FORMAT = "Wrong format of the command";

    private boolean isControllerInitialized = false;

    private static final Logger logger = LogManager.getLogger(EditCommand.class.getName());
    private EditCommandController editCommandController;

    public EditCommand(EditCommandController editCommandController) {
        this.editCommandController = editCommandController;
    }

    @Override
    public boolean isThisIt(String query) {
        return query.split(" ")[0].toLowerCase().equals(COMMAND);
    }

    @Override
    public void run(String commandString) {
        if (!isControllerInitialized) {
            editCommandController.init();

            isControllerInitialized = true;
        }

        String[] splitQuery = commandString.split(" ");

        if (splitQuery.length == 3) {
            try {
                int id = Integer.parseInt(splitQuery[2]);

                switch (splitQuery[1]) {
                    case COMPANY -> editCommandController.editCompany(id);
                    case DISCHARGE -> editCommandController.editDischarge(id);
                    case CLASSIFIED_POLLUTANT -> editCommandController.editClassifiedPollutant(id);
                    case DISCHARGED_POLLUTANT -> editCommandController.editDischargedPollutant(id);
                    default -> printUndefinedCommand();
                }
            } catch (NumberFormatException exc) {
                logger.error("Unable to parse the id", exc);

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
