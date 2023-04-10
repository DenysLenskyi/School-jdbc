package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.CourseDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentCourseDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentDao;

public class DeleteStudentFromCourseCommand implements Command {

    private static final String INCORRECT_COURSE_NAME = """
            Incorrect course name...
            Available courses: Math, English, Biologic, Geography, Chemistry,
                               Physics, History, Finance, Sports, Etiquette.
                               
            """;
    private static final String STUDENT_ID_IS_NOT_EXISTS = "There is no student with such id...";
    private static final String STUDENT_REMOVED_FROM_COURSE = "Student removed from course";
    private static final String NOTHING_TO_DELETE = "Nothing to delete... Please try again";

    @Override
    public void execute(CommandHolder commandHolder) {
        if (!(CourseDao.getAvailableCoursesNames().contains(commandHolder.getCourseName()))) {
            System.out.println(INCORRECT_COURSE_NAME);
        }
        if (!(StudentDao.getStudentDao().studentIdExistsInTable(commandHolder.getStudentId()))) {
            System.out.println(STUDENT_ID_IS_NOT_EXISTS);
        }
        if ((StudentCourseDao.getStudentCourseDao().studentEnrolledToCourse(commandHolder.getStudentId(),
                commandHolder.getCourseName()))) {
            StudentCourseDao.getStudentCourseDao()
                    .deleteStudentFromCourse(commandHolder.getStudentId(), commandHolder.getCourseName());
            System.out.println(STUDENT_REMOVED_FROM_COURSE);
        } else {
            System.out.println(NOTHING_TO_DELETE);
        }

    }
}