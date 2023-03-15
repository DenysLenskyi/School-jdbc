package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

public class CommandHolderBuilder extends CommandHolder {

    public static final String COURSE_ID = "--course_id";
    public static final String NUM_STUDENTS = "--num_students";

    public static CommandHolder buildCommandFromInputString(String commandText) {
        CommandHolder commandHolder = new CommandHolder();
        String[] commandData = commandText.split(StringConstant.WHITESPACE);
        commandHolder.commandName = commandData[0];
        if (commandData.length > 1) {
            for (String commandKeyword : commandData) {
                if (commandKeyword.contains(COURSE_ID)) {
                    String[] keywordValue = commandKeyword.split(StringConstant.EQUAL);
                    commandHolder.courseId = Integer.parseInt(keywordValue[1]);
                } else if (commandKeyword.contains(NUM_STUDENTS)) {
                    String[] keywordValue = commandKeyword.split(StringConstant.EQUAL);
                    commandHolder.numStudents = Integer.parseInt(keywordValue[1]);
                }
            }
        }
        return commandHolder;
    }
}