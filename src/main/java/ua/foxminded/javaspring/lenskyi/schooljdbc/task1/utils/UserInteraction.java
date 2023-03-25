package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandDefendant;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolderBuilder;

import java.util.Scanner;

public class UserInteraction {

    private static final String EXIT = "exit";
    private static final String INCORRECT_INPUT = "Incorrect input";
    private static final String WRONG_DATA_FORMAT = "You operate with wrong data, please check 'info'";

    private static CommandDefendant commandDefendant = new CommandDefendant();
    private static CommandHolder commandHolder;

    private UserInteraction() {
    }

    public static void runApp(Scanner scanner) {
        String userInput;
        while (true) {
            System.out.print('>');
            userInput = scanner.nextLine();
            if (EXIT.equals(userInput)) {
                break;
            }
            try {
                commandHolder = CommandHolderBuilder.buildCommandFromInputString(userInput);
                commandDefendant.getCommandByCode(commandHolder.getCommandName()).execute(commandHolder);
            } catch (Exception e) {
                System.out.println(INCORRECT_INPUT);
            }
        }
        scanner.close();
    }
}