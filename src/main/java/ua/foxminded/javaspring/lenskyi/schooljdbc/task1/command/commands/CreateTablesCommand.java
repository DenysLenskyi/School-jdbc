package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.QueryBuilder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.TableCreateDao;

public class CreateTablesCommand implements Command {

    static QueryBuilder queryBuilder = new QueryBuilder();
    public static final String SQL = queryBuilder.getInitiateTablesQuery();
    TableCreateDao tables = new TableCreateDao();

    @Override
    public void execute(CommandHolder ch) {
        tables.createNewTables(SQL);
    }
}