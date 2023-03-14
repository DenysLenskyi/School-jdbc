package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {

    public List<String> findCourseById(String sql) {
        List<String> output = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                output.add(String.valueOf(rs.getInt(1)));
                output.add(rs.getString(2));
                output.add(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }
}