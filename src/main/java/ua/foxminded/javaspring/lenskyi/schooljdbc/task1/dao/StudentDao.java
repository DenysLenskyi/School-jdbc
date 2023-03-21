package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Student;

import java.sql.*;
import java.util.List;

public class StudentDao {

    private static StudentDao studentDao = new StudentDao();

    private static final String ADD_STUDENTS_QUERY =
            "INSERT INTO school.student (group_id, first_name, last_name) VALUES (?,?,?)";
    private static final String ADD_NEW_STUDENT_QUERY =
            "insert into school.student (group_id, first_name, last_name) VALUES (?,?,?)";
    private static final String DELETE_STUDENT_BY_ID_QUERY = "delete from school.student where id = ?";

    private StudentDao() {
    }

    public static StudentDao getStudentDao() {
        return studentDao;
    }

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

    public void addNewStudent(int groupId, String firstName, String lastName) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_NEW_STUDENT_QUERY)) {
            statement.setInt(1, groupId);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentById(int studentId) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_BY_ID_QUERY)) {
            statement.setInt(1, studentId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}