package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandDefendant;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils.UserInteraction;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CommandDefendant commandDefendant = new CommandDefendant();
        commandDefendant.findCommand("100").execute();
        commandDefendant.findCommand("110").execute();
        //UserInteraction.runApp(scanner);
        //scanner.close();
        commandDefendant.getCommandByCode("info").execute();
    }
}