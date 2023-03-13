package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands.FindCourseByIdCommand;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands.UnknownCommand;

public class CommandHolderBuilder extends CommandHolder {

    private static final String FIND_COURSE = "find_course";


    public static CommandHolder buildCommandFromInputString(String commandText) {
        CommandHolder commandHolder = new CommandHolder();
        String[] commandData = commandText.split(StringConstant.WHITESPACE);
        commandHolder.commandName = commandData[0];
        return commandHolder;
    }
}