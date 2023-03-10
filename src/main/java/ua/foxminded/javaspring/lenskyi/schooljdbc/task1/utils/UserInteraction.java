package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandDefendant;

import java.util.Scanner;

public class UserInteraction {

    private UserInteraction() {
    }

    static CommandDefendant commandDefendant = new CommandDefendant();

    public static final String DISCLAIMER = "info - prints available commands";

    public static void runApp(Scanner scanner) {
        System.out.println(DISCLAIMER);
        String input = scanner.nextLine();
        commandDefendant.findCommand(input).execute();
        runApp(scanner);
    }
}