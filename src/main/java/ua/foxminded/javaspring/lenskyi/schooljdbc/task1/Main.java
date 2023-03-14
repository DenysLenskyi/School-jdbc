package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands.CreateTablesCommand;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands.PopulateTablesCommand;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils.UserInteraction;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    public static final String DISCLAIMER = "info - prints available commands";

    public static void main(String[] args) {
        Command createNewTables = new CreateTablesCommand();
        createNewTables.execute(new CommandHolder());
        Command populateTables = new PopulateTablesCommand();
        populateTables.execute(new CommandHolder());
        System.out.println(DISCLAIMER);
        UserInteraction.runApp(scanner);
        scanner.close();
    }
}