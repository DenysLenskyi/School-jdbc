package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.TableCreateDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.domain.QueryBuilder;

public class CreateTablesCommand implements Command {

    QueryBuilder queryBuilder = new QueryBuilder();
    TableCreateDao tables = new TableCreateDao();

    @Override
    public void execute() {
        queryBuilder.setInitiateTablesQuery();
        tables.createNewTables();
    }
}
