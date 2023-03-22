package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils;

import org.junit.jupiter.api.Test;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Course;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Group;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Student;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.StudentCourse;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils.RandomDataCreator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomDataCreatorTest {

    @Test
    public void generateGroupsListSizeTest() {
        List<Group> groups = RandomDataCreator.generateGroups(10);
        assertEquals(10, groups.size());
    }

    @Test
    public void generateGroupsIdsTest() {
        List<Group> groups = RandomDataCreator.generateGroups(10);
        assertEquals(1, groups.get(0).getId());
        assertEquals(2, groups.get(1).getId());
        assertEquals(3, groups.get(2).getId());
    }

    @Test
    public void generateGroupsNamesLengthTest() {
        List<Group> groups = RandomDataCreator.generateGroups(10);
        assertEquals(5, groups.get(0).getName().length());
    }

    @Test
    public void generateStudentsListSizeTest() {
        List<Student> students = RandomDataCreator.generateStudents(200);
        assertEquals(200, students.size());
    }

    @Test
    public void generateStudentsLastNameTest() {
        List<Student> students = RandomDataCreator.generateStudents(200);
        assertEquals(true, students.get(0).getLastName().contains("son"));
    }

    @Test
    public void generateStudentsGroupIdTest() {
        List<Student> students = RandomDataCreator.generateStudents(200);
        assertEquals(true, (students.get(students.size() - 1).getGroupId()) != 0);
    }

    @Test
    public void generateStudentCourseRelation() {
        List<StudentCourse> test = RandomDataCreator.generateStudentCourseRelation(10);
        assertEquals(true, test.size() > 0);
    }

    @Test
    public void getCoursesFromResourcesTest() {
        List<Course> courses = RandomDataCreator.getCoursesFromResources();
        assertEquals(10, courses.size());
        assertEquals("Math", courses.get(0).getName());
    }
}
