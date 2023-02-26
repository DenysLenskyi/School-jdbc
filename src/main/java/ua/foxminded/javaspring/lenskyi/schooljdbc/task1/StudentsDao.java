package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import java.sql.*;
import java.util.Random;

public class StudentsDao {

    FileReader reader = new FileReader();
    private final String URL = "jdbc:postgresql://localhost/SchoolJDBC";
    private final String USER = "postgres";
    private final String PASSWORD = "666";
    private final String INIT_TABLE = "/initiate-table-students.sql";
    private final String NAMES_TXT = "/names.txt";
    private final String INSERT_QUERY = "INSERT INTO school.students (student_id, group_id, first_name, last_name)";
    private final String SON = "son";

    public void createTable() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {
            statement.execute("DROP TABLE IF EXISTS school.students");
            statement.execute(reader.readFile(INIT_TABLE));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateTable() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {
            statement.execute(createSqlScriptToPopulateStudentsTable());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String createSqlScriptToPopulateStudentsTable() {
        GroupsDao groups = new GroupsDao();
        StringBuilder script = new StringBuilder();
        script.append(INSERT_QUERY).append(GroupsDao.NEWLINE)
                .append(GroupsDao.VALUES)
                .append(GroupsDao.NEWLINE);
        String[] names = reader.readFile(NAMES_TXT).split(GroupsDao.SEMICOLON);
        Random random = new Random();
        for (int i = 0; i < 200; i++) {
            script.append(groups.OPEN_BRACKET)
                    .append(i)
                    .append(groups.COMA)
                    .append(groups.WHITESPACE)
                    .append(i)
                    .append(groups.COMA)
                    .append(groups.WHITESPACE)
                    .append(groups.QUOTE)
                    .append(names[random.nextInt(39)])
                    .append(groups.QUOTE)
                    .append(groups.COMA)
                    .append(groups.WHITESPACE)
                    .append(groups.QUOTE)
                    .append(names[random.nextInt(39)])
                    .append(SON)
                    .append(groups.QUOTE)
                    .append(groups.CLOSE_BRACKET)
                    .append(groups.COMA)
                    .append(groups.NEWLINE);
        }
        return script.deleteCharAt(script.length() - 1)
                .deleteCharAt(script.length() - 1)
                .append(groups.SEMICOLON)
                .toString();
    }
}
