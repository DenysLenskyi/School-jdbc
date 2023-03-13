package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

public class CommandHolderBuilder extends CommandHolder {

    public static CommandHolder buildCommandFromInputString(String commandText) {
        CommandHolder commandHolder = new CommandHolder();
        String[] commandData = commandText.split(StringConstant.WHITESPACE);
        commandHolder.commandName = commandData[0];
        return commandHolder;
    }
}