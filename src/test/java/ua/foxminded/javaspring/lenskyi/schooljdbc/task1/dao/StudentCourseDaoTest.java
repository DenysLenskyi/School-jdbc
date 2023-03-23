package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import org.junit.jupiter.api.Test;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentCourseDaoTest {

    @Test
    public void getStudentsEnrolledToCoursePlainTest() {
        List<Student> students = StudentCourseDao.getStudentCourseDao()
                .getStudentsEnrolledToCourse("Math");
        assertEquals(1, students.size());
        assertEquals("Mark", students.get(0).getFirstName());
        assertEquals("Antony", students.get(0).getLastName());
        assertEquals(1, students.get(0).getId());
    }
}