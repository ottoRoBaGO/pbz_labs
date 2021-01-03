package tech.ottorobago.dokb_lab_2.ui;

public interface Command {
    boolean isThisIt(String query);

    void run(String commandString);
}
