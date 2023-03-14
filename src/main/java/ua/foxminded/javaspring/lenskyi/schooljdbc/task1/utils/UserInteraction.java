package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandDefendant;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolderBuilder;

import java.util.Scanner;

public class UserInteraction {

    private UserInteraction() {
    }

    static CommandDefendant commandDefendant = new CommandDefendant();
    static CommandHolder commandHolder;

    public static void runApp(Scanner scanner) {
        String input = scanner.nextLine();
        commandHolder = CommandHolderBuilder.buildCommandFromInputString(input);
        commandDefendant.getCommandByCode(commandHolder.getCommandName()).execute(commandHolder);
        runApp(scanner);
    }
}