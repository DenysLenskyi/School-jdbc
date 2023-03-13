package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.QueryBuilder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.CourseDao;

public class FindCourseByIdCommand implements Command {

    int courseId;

    public FindCourseByIdCommand(int courseId) {
        this.courseId = courseId;
    }

    CourseDao courseTable = new CourseDao();
    static QueryBuilder queryBuilder = new QueryBuilder();


    @Override
    public void execute(CommandHolder commandHolder) {
        final String SQL = queryBuilder.getFindCourseByIdScript(courseId);
        System.out.println(courseTable.findCourseById(SQL));
    }
}