package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.QueryBuilder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.StringConstant;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.GroupDao;

import java.util.List;

public class FindGroupsWithNumStudentsCommand implements Command {

    private static final String DISCLAIMER = "Groups with less or equal than ";
    private static final String STUDENTS = " students";
    static QueryBuilder queryBuilder = new QueryBuilder();
    GroupDao groupTable = new GroupDao();

    @Override
    public void execute(CommandHolder ch) {
        StringBuilder output = new StringBuilder();
        System.out.println(output.append(DISCLAIMER)
                .append(ch.getNumStudents())
                .append(STUDENTS)
                .append(StringConstant.NEWLINE)
                .append(groupTable.getGroupWithLessOrEqualAmountOfStudents(
                        queryBuilder.getFindGroupsWithNumStudentsScript(ch.getNumStudents()))));
    }
}