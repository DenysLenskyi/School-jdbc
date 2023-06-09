package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Student;

import java.sql.*;
import java.util.List;

public class StudentDao extends BaseDao {

    private static int minGroupId = 1;
    private static int maxGroupId = 10;
    private static StudentDao studentDao = new StudentDao();
    private static final String ADD_STUDENTS_QUERY =
            "INSERT INTO school.student (group_id, first_name, last_name) VALUES (?,?,?)";
    private static final String ADD_NEW_STUDENT_QUERY =
            "insert into school.student (group_id, first_name, last_name) VALUES (?,?,?)";
    private static final String DELETE_STUDENT_BY_ID_QUERY = "delete from school.student where id = ?";
    private static final String SELECT_BY_ID = "select * from school.student where id = ?";
    private static final String STUDENT_ADDED = "Student added";
    private static final String DISCLAIMER_AFTER_INCORRECT_INPUT = """
            Failed to add new student...
            Group id should be from 1 to 10 (0 to set null group)
            It is recommended to use real names for new students
            Do not use whitespaces
            Please try again
            """;

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
            if (groupId >= minGroupId && groupId <= maxGroupId) {
                statement.setInt(1, groupId);
            }
            if (groupId == 0) {
                statement.setNull(1, 0);
            }
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.execute();
            System.out.println(STUDENT_ADDED);
        } catch (SQLException e) {
            System.out.println(DISCLAIMER_AFTER_INCORRECT_INPUT);
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

    public boolean studentIdExistsInTable(int studentId) {
        boolean idExists = true;
        ResultSet rs = null;
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, studentId);
            rs = statement.executeQuery();
            if (!(rs.isBeforeFirst())) {
                idExists = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return idExists;
    }
}