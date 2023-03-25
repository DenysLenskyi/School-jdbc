package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.BaseDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils.FileReader;

public class CreateTablesCommand implements Command {

    private static FileReader reader = new FileReader();
    private static final String TABLES_INITIATION_SCRIPT_FILE_NAME = "/initiate-tables.sql";
    private static final String TABLES_INITIATION_SCRIPT = reader.readFile(TABLES_INITIATION_SCRIPT_FILE_NAME);

    @Override
    public void execute(CommandHolder commandHolder) {
        BaseDao.getBaseDao().executeScript(TABLES_INITIATION_SCRIPT);
    }
}