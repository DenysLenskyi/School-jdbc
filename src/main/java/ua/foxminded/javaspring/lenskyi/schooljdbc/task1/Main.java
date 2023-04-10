package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import org.postgresql.util.PSQLException;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands.CreateTablesCommand;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands.PopulateTablesCommand;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils.UserInteraction;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static final String DISCLAIMER = "info - prints available commands";

    public static void main(String[] args) {
        initiateDatabase();
        populateDatabase();
        printDisclaimer();
        UserInteraction.runApp(scanner);
        scanner.close();
    }

    private static void initiateDatabase() {
        Command createNewTables = new CreateTablesCommand();
        createNewTables.execute(new CommandHolder());
    }

    private static void populateDatabase() {
        Command populateTables = new PopulateTablesCommand();
        populateTables.execute(new CommandHolder());
    }

    private static void printDisclaimer() {
        System.out.println(DISCLAIMER);
    }
}