package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.QueryBuilder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.StringConstant;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentCourseDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Student;

import java.util.List;

public class FindStudentsEnrolledToCourseCommand implements Command {

    private static final String STUDENT_ID = "Student ID: ";
    private static final String STUDENT_FULL_NAME = "Student name: ";
    private static final String STUDENTS_ENROLLED_TO = "Students enrolled to ";
    private static final String COURSE = " course:";
    StudentCourseDao studentCourseTable = new StudentCourseDao();
    QueryBuilder queryBuilder = new QueryBuilder();

    @Override
    public void execute(CommandHolder commandHolder) {
        StringBuilder output = new StringBuilder();
        output.append(STUDENTS_ENROLLED_TO)
                .append(commandHolder.getCourseName())
                .append(COURSE)
                .append(StringConstant.NEWLINE);
        List<Student> queryResult = studentCourseTable.getStudentsEnrolledToCourse(
                queryBuilder.getFindStudentsEnrolledToCourseScript(commandHolder.getCourseName()));
        for (Student student : queryResult) {
            output.append(STUDENT_ID)
                    .append(student.getId())
                    .append(StringConstant.VERTICAL_BAR)
                    .append(STUDENT_FULL_NAME)
                    .append(student.getFirstName())
                    .append(StringConstant.WHITESPACE)
                    .append(student.getLastName())
                    .append(StringConstant.NEWLINE);
        }
        System.out.println(output);
    }
}