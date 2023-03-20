package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Student;

import java.sql.*;
import java.util.List;

public class StudentDao {

    private static final String ADD_STUDENTS_QUERY =
            "INSERT INTO school.student (group_id, first_name, last_name) VALUES (?,?,?)";

    public void addStudents(List<Student> students) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_STUDENTS_QUERY)) {
            connection.setAutoCommit(false);
            for (Student student : students) {
                if (student.getGroupId() == 0) {
                    statement.setNull(1, 0);
                } else {
                    statement.setInt(1, student.getGroupId());
                }
                statement.setString(2, student.getFirstName());
                statement.setString(3, student.getLastName());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addNewStudent(String script) {
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(script);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentById(String script) {
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(script);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}