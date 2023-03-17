package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.QueryBuilder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentCourseDao;

public class DeleteStudentFromCourseCommand implements Command {

    StudentCourseDao studentCourseTable = new StudentCourseDao();
    QueryBuilder queryBuilder = new QueryBuilder();
    private static final String STUDENT_REMOVED_FROM_COURSE = "Student removed from course";

    @Override
    public void execute(CommandHolder commandHolder) {
        studentCourseTable.deleteStudentFromCourse(queryBuilder
                .getDeleteStudentFromCourseScript(commandHolder.getStudentId(), commandHolder.getCourseName()));
        System.out.println(STUDENT_REMOVED_FROM_COURSE);
    }
}
