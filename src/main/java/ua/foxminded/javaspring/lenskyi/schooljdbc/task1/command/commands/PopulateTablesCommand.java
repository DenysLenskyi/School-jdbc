package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.QueryBuilder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.TablePopulateDao;

public class PopulateTablesCommand implements Command {

    TablePopulateDao tables = new TablePopulateDao();
    static QueryBuilder queryBuilder = new QueryBuilder();

    @Override
    public void execute(CommandHolder ch) {
        tables.populateTables(queryBuilder.getPopulateTablesQuery());
    }
}