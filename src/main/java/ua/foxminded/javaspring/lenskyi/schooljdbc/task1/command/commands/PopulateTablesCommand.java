package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.QueryBuilder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.GroupDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.TablePopulateDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Group;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Student;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils.RandomDataCreator;

import java.util.List;

public class PopulateTablesCommand implements Command {

    TablePopulateDao tables = new TablePopulateDao();
    static QueryBuilder queryBuilder = new QueryBuilder();

    GroupDao groupsTable = new GroupDao();
    StudentDao studentTable = new StudentDao();

    @Override
    public void execute(CommandHolder ch) {
        tables.populateTables(queryBuilder.getPopulateTablesQuery());
        List<Group> groups = RandomDataCreator.generateGroups(10);
        groupsTable.addGroups(groups);
        List<Student> students = RandomDataCreator.generateStudents(200);
        studentTable.addStudents(students);
    }
}