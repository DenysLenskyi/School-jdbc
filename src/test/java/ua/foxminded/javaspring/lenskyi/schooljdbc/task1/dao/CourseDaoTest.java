package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import org.junit.jupiter.api.Test;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseDaoTest {

    @Test
    public void findCourseById1Test() {
        Course test = CourseDao.getCourseDao().findCourseById(1);
        assertEquals(1, test.getId());
        assertEquals("Math", test.getName());
        assertEquals("Math", test.getDescription());
    }

    @Test
    public void findCourseById2Test() {
        Course test = CourseDao.getCourseDao().findCourseById(2);
        assertEquals(2, test.getId());
        assertEquals("Bio", test.getName());
        assertEquals("Bio", test.getDescription());
    }

    @Test
    public void findCourseById3Test() {
        Course test = CourseDao.getCourseDao().findCourseById(3);
        assertEquals(3, test.getId());
        assertEquals("Sport", test.getName());
        assertEquals("Sport", test.getDescription());
    }

    @Test
    public void findCourseByIdNoMatchTest() {
        Course test = CourseDao.getCourseDao().findCourseById(153);
        assertEquals(0, test.getId());
        assertEquals(null, test.getName());
        assertEquals(null, test.getDescription());
    }
}