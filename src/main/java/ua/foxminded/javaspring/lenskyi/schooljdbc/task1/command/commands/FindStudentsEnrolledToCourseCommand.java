package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentCourseDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class FindStudentsEnrolledToCourseCommand implements Command {

    private static final String STUDENT_ID = "Student ID:";
    private static final String STUDENT_FULL_NAME = "Student name:";
    private static final String FORMAT = "%1$s %2$s | %3$s %4$s %5$s";

    @Override
    public void execute(CommandHolder commandHolder) {
        List<String> output = new ArrayList<>();
        List<Student> queryResult = StudentCourseDao.getStudentCourseDao()
                .getStudentsEnrolledToCourse(commandHolder.getCourseName());
        for (Student student : queryResult) {
            String studentInfo = String.format(FORMAT, STUDENT_ID, student.getId(),
                    STUDENT_FULL_NAME, student.getFirstName(), student.getLastName());
            output.add(studentInfo);
        }
        output.forEach(System.out::println);
    }
}