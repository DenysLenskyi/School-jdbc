package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.CourseDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentCourseDao;

public class FindStudentsEnrolledToCourseCommand implements Command {

    private static final String STUDENT_ID = "Student ID:";
    private static final String STUDENT_FULL_NAME = "Student name:";
    private static final String FORMAT = "%1$s %2$s | %3$s %4$s %5$s";
    private static final String DISCLAIMER_AFTER_WRONG_INPUT = """
            Failed...
            Available courses: Math, English, Biologic, Geography, Chemistry,
                               Physics, History, Finance, Sports, Etiquette.
            """;

    @Override
    public void execute(CommandHolder commandHolder) {
        if (CourseDao.getAvailableCoursesNames().contains(commandHolder.getCourseName())) {
            StudentCourseDao.getStudentCourseDao().getStudentsEnrolledToCourse(commandHolder.getCourseName())
                    .stream()
                    .map(student -> String.format(FORMAT, STUDENT_ID, student.getId(),
                            STUDENT_FULL_NAME, student.getFirstName(), student.getLastName()))
                    .forEach(System.out::println);
            System.out.println('\n');
        } else {
            System.out.println(DISCLAIMER_AFTER_WRONG_INPUT);
        }
    }
}