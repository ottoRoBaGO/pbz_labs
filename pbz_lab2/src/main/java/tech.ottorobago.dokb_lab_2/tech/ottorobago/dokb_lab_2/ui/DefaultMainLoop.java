package tech.ottorobago.dokb_lab_2.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DefaultMainLoop implements MainLoop {
    private static final Logger logger = LogManager.getLogger(DefaultMainLoop.class.getName());
    private boolean doContinue = true;
    private List<Command> commands;
    private final String EXIT_WORD = "quit";
    private final String GREETINGS = "> ";
    private final String UNDEFINED_COMMAND = "Undefined command";

    public DefaultMainLoop() {
        commands = new ArrayList<>();
    }

    @Override
    public void run() {
        doContinue = true;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String command;

        while (doContinue) {
            try {
                System.out.print(GREETINGS);

                command = bufferedReader.readLine();

                if (command.equals(EXIT_WORD))
                    stop();
                else if (!command.isEmpty()) {
                    boolean found = false;

                    for (Command candidate : commands) {
                        if (candidate.isThisIt(command)) {
                            found = true;
                            candidate.run(command);

                            break;
                        }
                    }

                    if (!found)
                        System.out.println(UNDEFINED_COMMAND);
                }

                System.out.println();
            } catch (IOException exc) {
                logger.error("Unable to read command", exc);
            }
        }
    }

    @Override
    public void stop() {
        doContinue = false;
    }

    @Override
    public void addCommand(Command command) {
        commands.add(command);
    }
}
