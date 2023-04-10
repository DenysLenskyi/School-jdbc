package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentDao;

public class DeleteStudentByIdCommand implements Command {

    private static final String STUDENT_NOT_DELETED = "There is no student with such id in students table";
    private static final String STUDENT_DELETED = "Student deleted";

    @Override
    public void execute(CommandHolder commandHolder) {
        Boolean studentIdExists = StudentDao.getStudentDao().studentIdExistsInTable(commandHolder.getStudentId());
        if (Boolean.TRUE.equals(studentIdExists)) {
            StudentDao.getStudentDao().deleteStudentById(commandHolder.getStudentId());
            System.out.println(STUDENT_DELETED);
        } else {
            System.out.println(STUDENT_NOT_DELETED);
        }

    }
}