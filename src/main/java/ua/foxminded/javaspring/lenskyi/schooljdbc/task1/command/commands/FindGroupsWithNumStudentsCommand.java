package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.GroupDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Group;

import java.util.ArrayList;
import java.util.List;

public class FindGroupsWithNumStudentsCommand implements Command {

    private static final String DISCLAIMER = "Groups with less or equal than";
    private static final String STUDENTS = "students";
    private static final String GROUP_ID = "Group ID: ";
    private static final String GROUP_NAME = "Group name: ";
    private static final String FORMAT = "%1$s %2$s | %3$s %4$s";
    private static final String DISCLAIMER_FORMAT = "%1$s %2$s %3$s";

    @Override
    public void execute(CommandHolder commandHolder) {
        List<String> output = new ArrayList<>();
        List<Group> queryResult = GroupDao.getGroupDao()
                .getGroupWithLessOrEqualAmountOfStudents(commandHolder.getNumStudents());
        for (Group group : queryResult) {
            String groupInfo = String.format(FORMAT, GROUP_ID, group.getId(), GROUP_NAME, group.getName());
            output.add(groupInfo);
        }
        System.out.println(String.format(DISCLAIMER_FORMAT, DISCLAIMER, commandHolder.getNumStudents(), STUDENTS));
        output.forEach(System.out::println);
    }
}