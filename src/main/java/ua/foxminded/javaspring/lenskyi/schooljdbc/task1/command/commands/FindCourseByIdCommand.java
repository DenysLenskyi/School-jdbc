package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.CourseDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Course;

public class FindCourseByIdCommand implements Command {

    private static final String COURSE_ID = "Course ID: ";
    private static final String COURSE_NAME = "Course name: ";
    private static final String COURSE_DESCRIPTION = "Description: ";
    private static final String FORMAT = "%1$s %2$s | %3$s %4$s | %5$s %6$s";

    @Override
    public void execute(CommandHolder commandHolder) {
        Course course = CourseDao.getCourseDao().findCourseById(commandHolder.getCourseId());
        String str = String.format(
                FORMAT, COURSE_ID, course.getId(), COURSE_NAME, course.getName(),
                COURSE_DESCRIPTION, course.getDescription());
        System.out.println(str);
    }
}