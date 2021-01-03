package tech.ottorobago.dokb_lab_2.ui.stdcommands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.ui.Command;
import tech.ottorobago.dokb_lab_2.ui.stdcommands.controllers.ShowCommandController;

public class ShowCommand implements Command {
    private static final String COMMAND = "show";
    private final String DISCHARGE = "discharge";
    private final String UNDEFINED_COMMAND = "The subcommand is undefined";
    private final String WRONG_FORMAT = "Wrong format of the command";

    private boolean isControllerInitialized = false;

    private static final Logger logger = LogManager.getLogger(ShowCommand.class.getName());
    private ShowCommandController showCommandController;

    public ShowCommand(ShowCommandController showCommandController) {
        this.showCommandController = showCommandController;
    }

    @Override
    public boolean isThisIt(String query) {
        return query.split(" ")[0].toLowerCase().equals(COMMAND);
    }

    @Override
    public void run(String commandString) {
        if (!isControllerInitialized) {
            showCommandController.init();

            isControllerInitialized = true;
        }

        String[] splitQuery = commandString.split(" ");

        if (splitQuery.length == 3) {
            String subject = splitQuery[1];

            if (subject.equals(DISCHARGE)) {
                try {
                    int id = Integer.parseInt(splitQuery[2]);

                    showCommandController.showDischargesByCompany(id);
                } catch (NumberFormatException exc) {
                    logger.error("Input: " + splitQuery[2], exc);

                    printWrongFormat();
                }
            } else
                printUndefinedCommand();
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
