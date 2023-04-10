package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;

public class UnknownCommand implements Command {

    private static final String UNKNOWN_COMMAND = "Unknown command...";

    @Override
    public void execute(CommandHolder ch) {
        System.out.println(UNKNOWN_COMMAND);
    }
}
