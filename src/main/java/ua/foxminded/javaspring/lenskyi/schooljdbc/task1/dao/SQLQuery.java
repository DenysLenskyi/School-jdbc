package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.domain.FileReader;

public class SQLQuery {
    static FileReader reader = new FileReader();
    public static final String initiateTablesQuery = reader.readFile("/initiate-tables.sql");
}
