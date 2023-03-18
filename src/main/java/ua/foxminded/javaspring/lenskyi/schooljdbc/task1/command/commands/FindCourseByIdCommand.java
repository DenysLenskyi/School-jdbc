package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.QueryBuilder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.StringConstant;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.CourseDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Course;

public class FindCourseByIdCommand implements Command {

    private static final String COURSE_ID = "Course ID: ";
    private static final String COURSE_NAME = "Course name: ";
    private static final String COURSE_DESCRIPTION = "Description: ";

    CourseDao courseTable = new CourseDao();
    static QueryBuilder queryBuilder = new QueryBuilder();


    @Override
    public void execute(CommandHolder commandHolder) {
        StringBuilder output = new StringBuilder();
        Course course = courseTable.findCourseById(queryBuilder.getFindCourseByIdScript(commandHolder.getCourseId()));
        System.out.println(output.append(COURSE_ID)
                .append(course.getId())
                .append(StringConstant.VERTICAL_BAR)
                .append(COURSE_NAME)
                .append(course.getName())
                .append(StringConstant.VERTICAL_BAR)
                .append(COURSE_DESCRIPTION)
                .append(course.getDescription())
                .append(StringConstant.NEWLINE));
    }
}