package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentCourseDao;

public class AddStudentToCourseCommand implements Command {

    private static final String STUDENT_ADDED_TO_COURSE = "Student enrolled to course";

    @Override
    public void execute(CommandHolder commandHolder) {
        StudentCourseDao.getStudentCourseDao()
                .addStudentToCourse(commandHolder.getStudentId(), commandHolder.getCourseName());
        System.out.println(STUDENT_ADDED_TO_COURSE);
    }
}