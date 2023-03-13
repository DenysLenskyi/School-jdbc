package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandDefendant {

    static Map<String, Command> commandCode = new HashMap<>();
    public static final String INFO = "info";
    public static final String EXIT = "exit";
    public static final String FIND_COURSE_BY_ID = "find_course";

    static {
        commandCode.put(INFO, new InfoCommand());
        commandCode.put(EXIT, new ExitCommand());
        commandCode.put(FIND_COURSE_BY_ID, new FindCourseByIdCommand());
    }

    public Command getCommandByCode(String code) {
        return commandCode.get(code);
    }
}