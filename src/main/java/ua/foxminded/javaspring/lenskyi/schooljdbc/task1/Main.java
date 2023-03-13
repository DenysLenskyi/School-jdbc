package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandDefendant;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolderBuilder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils.UserInteraction;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CommandDefendant commandDefendant = new CommandDefendant();
        CommandHolder commandHolder = CommandHolderBuilder.buildCommandFromInputString("info");
        commandDefendant.getCommandByCode(commandHolder.getCommandName()).execute(commandHolder);
    }
}