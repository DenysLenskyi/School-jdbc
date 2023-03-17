package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {

    private static final String SQL_INSERT_INTO_GROUP_TABLE = "INSERT INTO school.group (id, name)";
    private static final String NEWLINE = "\n";
    private static final String VALUES = "VALUES";
    private static final String DEFAULT = "default";
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";
    private static final String WHITESPACE = " ";
    private static final String COMA = ",";
    private static final String QUOTE = "'";
    private static final String SEMICOLON = ";";

    public void addGroups(List<Group> groups) {
        StringBuilder script = new StringBuilder();
        script.append(SQL_INSERT_INTO_GROUP_TABLE)
                .append(NEWLINE)
                .append(VALUES)
                .append(NEWLINE);
        for (Group group : groups) {
            script.append(OPEN_BRACKET)
                    .append(DEFAULT)
                    .append(COMA)
                    .append(WHITESPACE)
                    .append(QUOTE)
                    .append(group.getGroupName())
                    .append(QUOTE)
                    .append(CLOSE_BRACKET)
                    .append(COMA)
                    .append(NEWLINE);
        }
        script.deleteCharAt(script.length() - 1)
                .deleteCharAt(script.length() - 1)
                .append(SEMICOLON);
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(script.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Group> getGroupWithLessOrEqualAmountOfStudents(String script) {
        List<Group> output = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(script);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Group group = new Group();
                group.setGroupId(rs.getInt(1));
                group.setGroupName(rs.getString(2));
                output.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }
}