package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandDefendant;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolderBuilder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands.CreateTablesCommand;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands.PopulateTablesCommand;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils.UserInteraction;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Command createNewTables = new CreateTablesCommand();
        createNewTables.execute(new CommandHolder());
        Command populateTables = new PopulateTablesCommand();
        populateTables.execute(new CommandHolder());
        CommandDefendant commandDefendant = new CommandDefendant();
        CommandHolder commandHolder = CommandHolderBuilder.buildCommandFromInputString("info");
        commandDefendant.getCommandByCode(commandHolder.getCommandName()).execute(commandHolder);

        CommandHolder commandHolder1 = CommandHolderBuilder
                .buildCommandFromInputString("find_course --course_id=7");
        commandDefendant.getCommandByCode(commandHolder1.getCommandName()).execute(commandHolder1);
    }
}