package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Student;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.StudentCourse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDao extends BaseDao {

    private static StudentCourseDao studentCourseDao = new StudentCourseDao();

    private static final String ADD_STUDENTS_COURSES_QUERY =
            "INSERT INTO school.student_course (STUDENT_ID, COURSE_ID) VALUES (?,?)";
    private static final String FIND_STUDENTS_ENROLLED_TO_COURSE_QUERY = """
            select distinct s.id, first_name, last_name
                from school.student_course s_c
                inner join school.course c on course_id = c.id
                inner join school.student s on s_c.student_id = s.id
                where c.name = ?
            """;
    private static final String ADD_STUDENT_TO_COURSE_QUERY = """
            insert into school.student_course (student_id, course_id)
            select ?, c.id
            from school.course c
            where c.name = ?
            """;
    private static final String REMOVE_STUDENT_FROM_COURSE = """
            delete from school.student_course
            where student_id = ? and course_id in (select
            id from school.course where name = ?)
            """;

    private StudentCourseDao() {
    }

    public static StudentCourseDao getStudentCourseDao() {
        return studentCourseDao;
    }

    public void addStudentCourses(List<StudentCourse> studentsCourses) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_STUDENTS_COURSES_QUERY)) {
            connection.setAutoCommit(false);
            for (StudentCourse studentCourse : studentsCourses) {
                statement.setInt(1, studentCourse.getStudentId());
                statement.setInt(2, studentCourse.getCourseId());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Student> getStudentsEnrolledToCourse(String courseName) {
        List<Student> output = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_STUDENTS_ENROLLED_TO_COURSE_QUERY)) {
            statement.setString(1, courseName);
            rs = statement.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt(1));
                student.setFirstName(rs.getString(2));
                student.setLastName(rs.getString(3));
                output.add(student);
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
        return output;
    }

    public void addStudentToCourse(int studentId, String courseName) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_STUDENT_TO_COURSE_QUERY)) {
            statement.setInt(1, studentId);
            statement.setString(2, courseName);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentFromCourse(int studentId, String courseName) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_STUDENT_FROM_COURSE)) {
            statement.setInt(1, studentId);
            statement.setString(2, courseName);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}