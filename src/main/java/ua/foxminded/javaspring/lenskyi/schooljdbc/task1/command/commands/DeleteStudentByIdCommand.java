package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.QueryBuilder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentDao;

public class DeleteStudentByIdCommand implements Command {

    StudentDao studentTable = new StudentDao();
    QueryBuilder queryBuilder = new QueryBuilder();
    private static final String STUDENT_DELETED = "Student deleted";

    @Override
    public void execute(CommandHolder commandHolder) {
        String script = queryBuilder.getDeleteStudentByIdScript(commandHolder.getStudentId());
        if (script != null) {
            studentTable.deleteStudentById(script);
            System.out.println(STUDENT_DELETED);
        }
    }
}