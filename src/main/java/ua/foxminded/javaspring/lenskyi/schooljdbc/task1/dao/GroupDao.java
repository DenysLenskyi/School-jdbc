package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {

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