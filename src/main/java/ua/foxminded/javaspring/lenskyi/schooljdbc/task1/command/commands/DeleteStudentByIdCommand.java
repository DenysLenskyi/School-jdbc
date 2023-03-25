package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentDao;

public class DeleteStudentByIdCommand implements Command {

    @Override
    public void execute(CommandHolder commandHolder) {
        StudentDao.getStudentDao().deleteStudentById(commandHolder.getStudentId());
    }
}