package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandDefendant {
    private static Command unknownCommand = new UnknownCommand();
    private static Map<String, Command> commandCode = new HashMap<>();
    private static final String INFO = "info";
    private static final String FIND_COURSE_BY_ID = "find_course";
    private static final String FIND_GROUPS = "find_groups";
    private static final String FIND_STUDENTS_COURSE = "find_students_course";
    private static final String ADD_STUDENT = "add_student";
    private static final String DELETE_STUDENT = "delete_student";
    private static final String ADD_STUDENT_COURSE = "add_student_course";
    private static final String DELETE_STUDENT_COURSE = "delete_student_course";


    static {
        commandCode.put(INFO, new InfoCommand());
        commandCode.put(FIND_COURSE_BY_ID, new FindCourseByIdCommand());
        commandCode.put(FIND_GROUPS, new FindGroupsWithNumStudentsCommand());
        commandCode.put(FIND_STUDENTS_COURSE, new FindStudentsEnrolledToCourseCommand());
        commandCode.put(ADD_STUDENT, new AddNewStudentCommand());
        commandCode.put(DELETE_STUDENT, new DeleteStudentByIdCommand());
        commandCode.put(ADD_STUDENT_COURSE, new AddStudentToCourseCommand());
        commandCode.put(DELETE_STUDENT_COURSE, new DeleteStudentFromCourseCommand());
    }

    public static Map<String, Command> getCommandCode() {
        return commandCode;
    }

    public Command getCommandByCode(String code) {
        if (commandCode.containsKey(code)) {
            return commandCode.get(code);
        } else {
            return unknownCommand;
        }
    }
}