package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        TableCreater createTableExample = new TableCreater();
        createTableExample.createTable();
        createTableExample.executeQuery("select * from school.courses");
    }
}
