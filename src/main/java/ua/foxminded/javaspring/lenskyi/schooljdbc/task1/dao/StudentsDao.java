package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.FileReader;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Random;

public class StudentsDao {

    private Random random;

    {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    FileReader reader = new FileReader();
    private final String URL = "jdbc:postgresql://localhost/SchoolJDBC";
    private final String USER = "postgres";
    private final String PASSWORD = "666";
    private final String INIT_TABLE = "/initiate-table-students.sql";
    private final String NAMES_TXT = "/names.txt";
    private final String INSERT_QUERY = "INSERT INTO school.students (student_id, group_id, first_name, last_name)";
    private final String SON = "son";
    private final String DEFAULT = "DEFAULT";
    private final String NULL = "NULL";

    public void createTable() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {
            statement.execute("DROP TABLE IF EXISTS school.students CASCADE");
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
        int totalNumStudentsAssignedToGroups = 0;
        int groupId = 0;
        while (totalNumStudentsAssignedToGroups <= 200) {
            groupId++;
            int randomNumStudentsForGroup = random.nextInt(10, 31);
            totalNumStudentsAssignedToGroups += randomNumStudentsForGroup;
            if (totalNumStudentsAssignedToGroups > 200) {
                int lastStudentGroup = 200 - (totalNumStudentsAssignedToGroups - randomNumStudentsForGroup);
                randomNumStudentsForGroup = lastStudentGroup;
            }
            for (int i = 0; i < randomNumStudentsForGroup; i++) {
                script.append(groups.OPEN_BRACKET)
                        .append(DEFAULT)
                        .append(groups.COMA)
                        .append(groups.WHITESPACE);
                if (groupId > 10) {
                    script.append(NULL);
                } else {
                    script.append(groupId);
                }
                script.append(groups.COMA)
                        .append(groups.WHITESPACE)
                        .append(groups.QUOTE)
                        .append(names[random.nextInt(40)])
                        .append(groups.QUOTE)
                        .append(groups.COMA)
                        .append(groups.WHITESPACE)
                        .append(groups.QUOTE)
                        .append(names[random.nextInt(40)])
                        .append(SON)
                        .append(groups.QUOTE)
                        .append(groups.CLOSE_BRACKET)
                        .append(groups.COMA)
                        .append(GroupsDao.NEWLINE);
            }
        }
        return script.deleteCharAt(script.length() - 1)
                .deleteCharAt(script.length() - 1)
                .append(GroupsDao.SEMICOLON)
                .toString();
    }
}
