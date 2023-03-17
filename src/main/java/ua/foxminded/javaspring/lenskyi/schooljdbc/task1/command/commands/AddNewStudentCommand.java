package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.QueryBuilder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentDao;

public class AddNewStudentCommand implements Command {

    StudentDao studentTable = new StudentDao();
    QueryBuilder queryBuilder = new QueryBuilder();
    private static final String STUDENT_ADDED = "Student added";

    @Override
    public void execute(CommandHolder commandHolder) {
        String script = queryBuilder.getAddNewStudentScript(
                commandHolder.getGroupId(), commandHolder.getStudentFirstName(), commandHolder.getStudentLastName());
        if (script != null) {
            studentTable.addNewStudent(script);
            System.out.println(STUDENT_ADDED);
        }
    }
}