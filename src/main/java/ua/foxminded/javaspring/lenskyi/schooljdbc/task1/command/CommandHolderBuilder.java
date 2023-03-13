package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

public class CommandHolderBuilder extends CommandHolder {

    public static final String COURSE_ID = "--course_id";

    public static CommandHolder buildCommandFromInputString(String commandText) {
        CommandHolder commandHolder = new CommandHolder();
        String[] commandData = commandText.split(StringConstant.WHITESPACE);
        commandHolder.commandName = commandData[0];
        if (commandData.length > 1) {
            for (String str : commandData) { //refactor
                if (str.contains(COURSE_ID)) {
                    String[] findCourse_courseId = str.split(StringConstant.EQUAL); // refactor!!
                    commandHolder.courseId = Integer.parseInt(findCourse_courseId[1]);
                }
            }
        }
        return commandHolder;
    }
}