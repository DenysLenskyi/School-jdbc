package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentDao;

public class AddNewStudentCommand implements Command {

    private static final String STUDENT_ADDED = "Student added";

    @Override
    public void execute(CommandHolder commandHolder) {
        StudentDao.getStudentDao().addNewStudent(
                commandHolder.getGroupId(), commandHolder.getStudentFirstName(), commandHolder.getStudentLastName());
        System.out.println(STUDENT_ADDED);
    }
}