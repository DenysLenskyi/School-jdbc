package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

public class CommandHolderBuilder extends CommandHolder {

    private static final String COURSE_ID = "--course_id";
    private static final String NUM_STUDENTS = "--num_students";
    private static final String COURSE_NAME = "--course_name";
    private static final String GROUP_ID = "--group_id";
    private static final String FIRST_NAME = "--first_name";
    private static final String LAST_NAME = "--last_name";
    private static final String STUDENT_ID = "--student_id";

    public static CommandHolder buildCommandFromInputString(String commandText) {
        CommandHolder commandHolder = new CommandHolder();
        String[] commandData = commandText.split(StringConstant.WHITESPACE);
        commandHolder.setCommandName(commandData[0]);
        if (commandData.length > 1) {
            for (String commandKeyword : commandData) {
                if (commandKeyword.contains(COURSE_ID)) {
                    String[] keywordValue = commandKeyword.split(StringConstant.EQUAL);
                    commandHolder.setCourseId(Integer.parseInt(keywordValue[1]));
                } else if (commandKeyword.contains(NUM_STUDENTS)) {
                    String[] keywordValue = commandKeyword.split(StringConstant.EQUAL);
                    commandHolder.setNumStudents(Integer.parseInt(keywordValue[1]));
                } else if (commandKeyword.contains(COURSE_NAME)) {
                    String[] keywordValue = commandKeyword.split(StringConstant.EQUAL);
                    commandHolder.setCourseName(keywordValue[1]);
                } else if (commandKeyword.contains(GROUP_ID)) {
                    String[] keywordValue = commandKeyword.split(StringConstant.EQUAL);
                    commandHolder.setGroupId(Integer.parseInt(keywordValue[1]));
                } else if (commandKeyword.contains(STUDENT_ID)) {
                    String[] keywordValue = commandKeyword.split(StringConstant.EQUAL);
                    commandHolder.setStudentId(Integer.parseInt(keywordValue[1]));
                } else if (commandKeyword.contains(FIRST_NAME)) {
                    String[] keywordValue = commandKeyword.split(StringConstant.EQUAL);
                    commandHolder.setStudentFirstName(keywordValue[1]);
                } else if (commandKeyword.contains(LAST_NAME)) {
                    String[] keywordValue = commandKeyword.split(StringConstant.EQUAL);
                    commandHolder.setStudentLastName(keywordValue[1]);
                }
            }
        }
        return commandHolder;
    }
}