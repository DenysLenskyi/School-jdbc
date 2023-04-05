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
    private static final String SELECT_BY_ID_AND_COURSE_NAME = """
            select * from school.student_course 
            where student_id = ? and course_id in (select
            id from school.course where name = ?)
            """;
    private static final String DISCLAIMER_AFTER_WRONG_INPUT = """
            Failed...
            Available courses: Math, English, Biologic, Geography, Chemistry,
                               Physics, History, Finance, Sports, Etiquette.
            Student id should be integer
            Do not previously delete student by it's id if you'd like to enroll this student to course
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
                for (Integer i : studentCourse.getCourseId()) {
                    statement.setInt(1, studentCourse.getStudentId());
                    statement.setInt(2, i);
                    statement.addBatch();
                }
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
            System.out.println(DISCLAIMER_AFTER_WRONG_INPUT);
        }
    }

    public void deleteStudentFromCourse(int studentId, String courseName) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_STUDENT_FROM_COURSE)) {
            statement.setInt(1, studentId);
            statement.setString(2, courseName);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(DISCLAIMER_AFTER_WRONG_INPUT);
        }
    }

    public boolean studentEnrolledToCourse(int studentId, String courseName) {
        boolean idExists = true;
        ResultSet rs = null;
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_AND_COURSE_NAME)) {
            statement.setInt(1, studentId);
            statement.setString(2, courseName);
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