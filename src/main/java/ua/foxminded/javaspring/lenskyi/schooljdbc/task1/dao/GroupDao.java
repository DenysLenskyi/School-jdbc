package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {

    private static final String GROUP_ID = "Group ID: ";
    private static final String GROUP_NAME = "Group name: ";
    private static final String NUM_STUDENTS = "Students in group: ";
    private static final String VERTICAL_BAR = " | ";
    private static final String NEWLINE = "\n";
    private static final String WHITESPACE = " ";

    public String getGroupWithLessOrEqualAmountOfStudents(String script) {
        StringBuilder output = new StringBuilder();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(script);
             ResultSet rs = preparedStatement.executeQuery()) {
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
        return output.deleteCharAt(output.length() - 1).toString();
    }
}