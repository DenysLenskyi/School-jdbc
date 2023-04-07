package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.TestDBPreparation;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Group;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentDaoTest {

    @BeforeAll
    static void setup() {
        TestDBPreparation.setupDB();
    }

    @Test
    public void deleteStudentPlainTest() {
        List<Group> groups = GroupDao.getGroupDao().getGroupWithLessOrEqualAmountOfStudents(7);
        assertEquals(2, groups.size());
        StudentDao.getStudentDao().deleteStudentById(2);
        List<Group> groups2 = GroupDao.getGroupDao().getGroupWithLessOrEqualAmountOfStudents(5);
        assertEquals(1, groups2.size());
        assertEquals("BB-50", groups2.get(0).getName());
    }

    @Test
    void studentIdExistsInTableTrueTest() {
        assertTrue(StudentDao.getStudentDao().studentIdExistsInTable(1));
    }

    @Test
    void studentIdExistsInTableFalseTest() {
        assertTrue(!(StudentDao.getStudentDao().studentIdExistsInTable(100500)));
    }

    @Test
    void addNewStudentTest() {
        StudentDao.getStudentDao().addNewStudent(2, "Davie", "Jones");
        StudentDao.getStudentDao().addNewStudent(2, "Davie", "Jones");
        List<Group> groups = GroupDao.getGroupDao().getGroupWithLessOrEqualAmountOfStudents(2);
        assertTrue(groups.size() == 0);
        assertTrue(StudentDao.getStudentDao().studentIdExistsInTable(9));
    }
}