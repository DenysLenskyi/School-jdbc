package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandDefendant;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolderBuilder;

import java.util.Scanner;

public class UserInteraction {

    private UserInteraction() {
    }

    private static final String INITIAL_INPUT = "initial input";

    static CommandDefendant commandDefendant = new CommandDefendant();
    static CommandHolder commandHolder;

    public static void runApp(Scanner scanner) {
        String input = INITIAL_INPUT;
        while (!(input.equals(CommandDefendant.EXIT))) {
            input = scanner.nextLine();
            commandHolder = CommandHolderBuilder.buildCommandFromInputString(input);
            commandDefendant.getCommandByCode(commandHolder.getCommandName()).execute(commandHolder);
        }
        scanner.close();
    }
}