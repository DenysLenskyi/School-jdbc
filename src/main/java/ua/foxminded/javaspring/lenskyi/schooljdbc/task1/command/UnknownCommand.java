package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

public class UnknownCommand implements Command {

    public static final String UNKNOWN_COMMAND = "Unknown command...";

    @Override
    public void execute() {
        System.out.println(UNKNOWN_COMMAND);
    }
}
