package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.domain.FileReader;

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
    private final String NEWLINE = "\n";
    private static final String GROUP_ID = "Group ID: ";
    private static final String VERTICAL_BAR = " | ";
    private static final String COMA = ",";
    private static final String CLOSE_BRACKET = ")";
    private static final String GROUP_NAME = "Group name: ";
    private static final String NUM_STUDENTS = "Students in group: ";
    private static final String WHITESPACE = " ";
    private static final String SEMICOLON = ";";
    private static final String QUOTE = "'";
    private static final String SQL_GROUPS_WITH_EQUAL_OR_LESS_NUM_STUDENTS = """
            select group_id, name, count(student_id)
             from school.students
             inner join school.groups on group_id = id
             group by name, group_id
             having count(student_id) <= """;
    private static final String SQL_DELETE_STUDENT_BY_ID = "delete from school.students where student_id = ";
    private static final String SQL_ADD_NEW_STUDENT = "insert into school.students values (default, ";

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

    public String getGroupWithAmountOfStudents(int numStudents) {
        StringBuilder output = new StringBuilder();
        StringBuilder script = new StringBuilder();
        script.append(SQL_GROUPS_WITH_EQUAL_OR_LESS_NUM_STUDENTS)
                .append(numStudents)
                .append(" order by group_id;");
        output.append("Groups with less or equal than ")
                .append(numStudents)
                .append(" students")
                .append(NEWLINE);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script.toString());
             ResultSet rs = preparedStatement.executeQuery();) {
            while (rs.next()) {
                output.append(GROUP_ID)
                        .append(rs.getInt(1))
                        .append(VERTICAL_BAR)
                        .append(WHITESPACE)
                        .append(GROUP_NAME)
                        .append(rs.getString(2))
                        .append(VERTICAL_BAR)
                        .append(WHITESPACE)
                        .append(NUM_STUDENTS)
                        .append(rs.getInt(3))
                        .append(VERTICAL_BAR)
                        .append(NEWLINE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public void addNewStudent(int groupId, String firstName, String lastName) {
        if (groupId <= 10 && groupId >= 0) {
            StringBuilder script = new StringBuilder();
            script.append(SQL_ADD_NEW_STUDENT);
            if (groupId == 0) {
                script.append(NULL);
            } else {
                script.append(groupId);
            }
            script.append(COMA)
                    .append(WHITESPACE)
                    .append(QUOTE)
                    .append(firstName)
                    .append(QUOTE)
                    .append(COMA)
                    .append(WHITESPACE)
                    .append(QUOTE)
                    .append(lastName)
                    .append(QUOTE)
                    .append(CLOSE_BRACKET)
                    .append(SEMICOLON);
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement statement = connection.createStatement();) {
                statement.execute(script.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Student added");
        } else {
            System.out.println("Group ID should be a number in range from 0 to 10");
        }
    }

    public void deleteStudentById(int studentId) {
        StringBuilder script = new StringBuilder();
        script.append(SQL_DELETE_STUDENT_BY_ID)
                .append(studentId)
                .append(SEMICOLON);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {
            statement.execute(script.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Student deleted");
    }
}