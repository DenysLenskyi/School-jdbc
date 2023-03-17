package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandDefendant;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolderBuilder;

import java.util.Scanner;

public class UserInteraction {

    private UserInteraction() {
    }

    public static final String EXIT = "exit";

    static CommandDefendant commandDefendant = new CommandDefendant();
    static CommandHolder commandHolder;

    public static void runApp(Scanner scanner) {
        String userInput;
        while (true) {
            userInput = scanner.nextLine();
            if (EXIT.equals(userInput)) {
                break;
            }
            commandHolder = CommandHolderBuilder.buildCommandFromInputString(userInput);
            commandDefendant.getCommandByCode(commandHolder.getCommandName()).execute(commandHolder);
        }
        scanner.close();
    }
}