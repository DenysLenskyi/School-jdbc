package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.TableCreateDao;

public class CreateTablesCommand implements Command {

    static QueryBuilder queryBuilder = new QueryBuilder();
    public static final String SQL = queryBuilder.getInitiateTablesQuery();
    TableCreateDao tables = new TableCreateDao();

    @Override
    public void execute() {
        tables.createNewTables(SQL);
    }
}