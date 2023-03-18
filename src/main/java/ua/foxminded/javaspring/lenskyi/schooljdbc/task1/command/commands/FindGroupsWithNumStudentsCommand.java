package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.QueryBuilder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.StringConstant;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.GroupDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Group;

import java.util.List;

public class FindGroupsWithNumStudentsCommand implements Command {

    private static final String DISCLAIMER = "Groups with less or equal than ";
    private static final String STUDENTS = " students";
    private static final String GROUP_ID = "Group ID: ";
    private static final String GROUP_NAME = "Group name: ";
    static QueryBuilder queryBuilder = new QueryBuilder();
    GroupDao groupTable = new GroupDao();

    @Override
    public void execute(CommandHolder commandHolder) {
        StringBuilder output = new StringBuilder();
        output.append(DISCLAIMER)
                .append(commandHolder.getNumStudents())
                .append(STUDENTS)
                .append(StringConstant.NEWLINE);
        List<Group> queryResult = groupTable.getGroupWithLessOrEqualAmountOfStudents(
                queryBuilder.getFindGroupsWithNumStudentsScript(commandHolder.getNumStudents()));
        for (Group group : queryResult) {
            output.append(GROUP_ID)
                    .append(group.getId())
                    .append(StringConstant.VERTICAL_BAR)
                    .append(GROUP_NAME)
                    .append(group.getName())
                    .append(StringConstant.NEWLINE);
        }
        System.out.println(output);
    }
}