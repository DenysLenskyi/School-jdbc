package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentDao;

public class DeleteStudentByIdCommand implements Command {

    private static final String STUDENT_DELETED = "Student deleted";

    @Override
    public void execute(CommandHolder commandHolder) {
        StudentDao.getStudentDao().deleteStudentById(commandHolder.getStudentId());
        System.out.println(STUDENT_DELETED);
    }
}