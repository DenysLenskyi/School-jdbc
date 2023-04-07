package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.TestDBPreparation;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentCourseDaoTest {

    @BeforeAll
    static void setup() {
        TestDBPreparation.setupDB();
    }

    @Test
    public void getStudentsEnrolledToCoursePlainTest() {
        List<Student> students = StudentCourseDao.getStudentCourseDao()
                .getStudentsEnrolledToCourse("Math");
        assertEquals(1, students.size());
        assertEquals("Mark", students.get(0).getFirstName());
        assertEquals("Antony", students.get(0).getLastName());
        assertEquals(1, students.get(0).getId());
    }

    @Test
    public void addStudentToCourseTest() {
        StudentCourseDao.getStudentCourseDao().addStudentToCourse(1, "Sport");
        assertTrue(StudentCourseDao.getStudentCourseDao().studentEnrolledToCourse(1, "Sport"));
    }

    @Test
    public void deleteStudentFromCourseTest() {
        StudentCourseDao.getStudentCourseDao().deleteStudentFromCourse(1, "Math");
        assertTrue(!(StudentCourseDao.getStudentCourseDao().studentEnrolledToCourse(1, "Math")));
    }
}