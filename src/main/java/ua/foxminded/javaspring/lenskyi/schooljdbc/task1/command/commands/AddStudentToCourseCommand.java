package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.CourseDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentCourseDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentDao;

public class AddStudentToCourseCommand implements Command {

    private static final String STUDENT_ADDED_TO_COURSE = "Student added to course";
    private static final String WRONG_INPUT = """
            Failed...
            - Check if there is student with such id
            - Available courses to enroll a student:
                               Math, English, Biologic, Geography, Chemistry,
                               Physics, History, Finance, Sports, Etiquette.
            """;

    @Override
    public void execute(CommandHolder commandHolder) {
        if (StudentDao.getStudentDao().studentIdExistsInTable(commandHolder.getStudentId()) &&
                CourseDao.getAvailableCoursesNames().contains(commandHolder.getCourseName())) {
            StudentCourseDao.getStudentCourseDao()
                    .addStudentToCourse(commandHolder.getStudentId(), commandHolder.getCourseName());
            System.out.println(STUDENT_ADDED_TO_COURSE);
        } else {
            System.out.println(WRONG_INPUT);
        }
    }
}