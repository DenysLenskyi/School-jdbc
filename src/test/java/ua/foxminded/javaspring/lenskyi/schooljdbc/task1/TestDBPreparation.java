package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.BaseDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils.FileReader;

public class TestDBPreparation {

    static FileReader reader = new FileReader();
    private static final String initTablesFileName = "/initTestTables.sql";
    private static final String popTablesFileName = "/popTestTables.sql";
    private static final String initTablesScript = reader.readFile(initTablesFileName);
    private static final String popTablesScript = reader.readFile(popTablesFileName);

    public static void setupDB() {
        BaseDao.getBaseDao().executeScript(initTablesScript);
        BaseDao.getBaseDao().executeScript(popTablesScript);
    }
}