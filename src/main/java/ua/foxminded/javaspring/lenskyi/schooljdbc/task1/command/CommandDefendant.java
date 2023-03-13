package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandDefendant {

    static Map<String, Command> commandCode = new HashMap<>();

    static Command infoCommand = new InfoCommand();

    public static final String INITIATE_TABLES = "100";
    public static final String POPULATE_TABLES = "110";
    public static final String INFO = "info";
    public static final String EXIT = "exit";
    public static final String FIND_COURSE_BY_ID = "findcourse";

    static {
        commandCode.put(INFO, new InfoCommand());
    }

    public Command getCommandByCode(String code) {
        return commandCode.get(code);
    }
}