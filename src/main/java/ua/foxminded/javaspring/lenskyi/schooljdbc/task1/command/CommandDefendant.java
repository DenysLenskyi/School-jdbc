package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

import java.util.Arrays;
import java.util.List;

public class CommandDefendant {

    public static final String INITIATE_TABLES = "100";
    public static final String POPULATE_TABLES = "110";
    public static final String INFO = "info";
    public static final String EXIT = "exit";
    public static final String FIND_COURSE_BY_ID = "findcourse";

    public Command findCommand(String input) {
        List<String> commandData;
        if (input.contains(StringConstant.UNDERSCORE)) {
            commandData = Arrays.stream(input.split(StringConstant.UNDERSCORE)).toList();
            if (commandData.get(0).equals(FIND_COURSE_BY_ID)) {
                return new FindCourseByIdCommand(Integer.parseInt(commandData.get(1)));
            }
        } else {
            if (input.equals(INITIATE_TABLES)) {
                return new CreateTablesCommand();
            } else if (input.equals(POPULATE_TABLES)) {
                return new PopulateTablesCommand();
            } else if (input.equals(INFO)) {
                return new InfoCommand();
            } else if (input.equals(EXIT)) {
                System.exit(0);
            } else {
                return new UnknownCommand();
            }
        }
        return new UnknownCommand();
    }
}