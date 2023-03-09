package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.CourseDao;

public class FindCourseByIdCommand implements Command {

    int courseId;

    public FindCourseByIdCommand(int courseId) {
        this.courseId = courseId;
    }

    CourseDao courseTable = new CourseDao();
    static QueryBuilder queryBuilder = new QueryBuilder();


    @Override
    public void execute() {
        final String SQL = queryBuilder.getFindCourseByIdScript(courseId);
        System.out.println(courseTable.findCourseById(SQL));
    }
}