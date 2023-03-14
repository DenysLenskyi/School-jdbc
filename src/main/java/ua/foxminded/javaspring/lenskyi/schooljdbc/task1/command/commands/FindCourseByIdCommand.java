package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.QueryBuilder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.StringConstant;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.CourseDao;

import java.util.List;

public class FindCourseByIdCommand implements Command {

    private static final String COURSE_ID = "Course ID: ";
    private static final String COURSE_NAME = "Course name: ";
    private static final String COURSE_DESCRIPTION = "Description: ";

    CourseDao courseTable = new CourseDao();
    static QueryBuilder queryBuilder = new QueryBuilder();


    @Override
    public void execute(CommandHolder commandHolder) {
        final String SQL = queryBuilder.getFindCourseByIdScript(commandHolder.getCourseId());
        List<String> queryOutput = courseTable.findCourseById(SQL);
        StringBuilder output = new StringBuilder();
        output.append(COURSE_ID)
                .append(queryOutput.get(0))
                .append(StringConstant.VERTICAL_BAR)
                .append(COURSE_NAME)
                .append(queryOutput.get(1))
                .append(StringConstant.VERTICAL_BAR)
                .append(COURSE_DESCRIPTION)
                .append(queryOutput.get(2));
        System.out.println(output);
    }
}