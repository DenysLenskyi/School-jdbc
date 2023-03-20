package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {

    private static final String ADD_GROUPS_QUERY = "INSERT INTO school.group (name) VALUES (?)";

    public void addGroups(List<Group> groups) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_GROUPS_QUERY)) {
            connection.setAutoCommit(false);
            for (Group group : groups) {
                statement.setString(1, group.getName());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
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
                group.setId(rs.getInt(1));
                group.setName(rs.getString(2));
                output.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }
}