package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;

public class ExitCommand implements Command {

    @Override
    public void execute(CommandHolder ch) {
        System.exit(0);
    }
}
